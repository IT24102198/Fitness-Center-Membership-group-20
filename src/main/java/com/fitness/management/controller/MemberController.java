package com.fitness.management.controller;

import com.fitness.management.model.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/members")
public class MemberController {

    private final List<Member> memberList = new ArrayList<>();

    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("member", new Member());
        model.addAttribute("members", memberList);
        return "insert";
    }

    @PostMapping("/add")
    public String addMember(@ModelAttribute Member member) {
        memberList.add(member);
        insertionSortByRenewalDate(memberList);
        return "redirect:/members/new";
    }

    @GetMapping("/delete/{id}")
    public String deleteMember(@PathVariable String id) {
        memberList.removeIf(m -> m.getMembershipId().equals(id));
        return "redirect:/members/new";
    }

    @GetMapping("/edit/{id}")
    public String editMember(@PathVariable String id, Model model) {
        Member member = memberList.stream()
                .filter(m -> m.getMembershipId().equals(id))
                .findFirst()
                .orElse(null);
        if (member == null) {
            return "redirect:/members/new";
        }
        model.addAttribute("member", member);
        return "edit";
    }

    @PostMapping("/update")
    public String updateMember(@ModelAttribute Member updatedMember) {
        memberList.removeIf(m -> m.getMembershipId().equals(updatedMember.getMembershipId()));
        memberList.add(updatedMember);
        insertionSortByRenewalDate(memberList);
        return "redirect:/members/new";
    }

    private void insertionSortByRenewalDate(List<Member> list) {
        for (int i = 1; i < list.size(); i++) {
            Member key = list.get(i);
            int j = i - 1;
            while (j >= 0 && list.get(j).getLastRenewalDate().isAfter(key.getLastRenewalDate())) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, key);
        }
    }
}

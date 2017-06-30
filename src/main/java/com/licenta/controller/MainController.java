package com.licenta.controller;

import com.licenta.dao.*;
import com.licenta.form.*;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class MainController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model){
        model.addAttribute("formAcorduri", new FormAcorduri());
        model.addAttribute("formOptiuni", new FormOptiuni());
        model.addAttribute("formProgram", new FormProgram());
        model.addAttribute("formSelectie", new FormSelectie());
        model.addAttribute("formAcorduriPrecedente", new FormAcorduriPrecedente());
        model.addAttribute("formAdaugareAcorduri",new FormAdaugareAcorduri());
        model.addAttribute("formAdaugareParteneri", new FormAdaugarePartener());
        model.addAttribute("formAdaugareOptiuniDosare", new FormAdaugareOptiuniDosare());
        model.addAttribute("formAdaugareStudenti", new FormAdaugareStudent());
        model.addAttribute("formStergePartener", new FormStergePartener());

        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("appconfig-database.xml");
        PersoaneDAO persoaneDAO = (PersoaneDAO)  ctx.getBean("persoaneDAO");
        ParteneriDAO parteneriDAO = (ParteneriDAO) ctx.getBean("parteneriDAO");
        SpecializariDAO specializariDAO = (SpecializariDAO) ctx.getBean("specializariDAO");
        List<String> studenti = persoaneDAO.findAllStudenti();
        model.addAttribute("studenti", studenti);
        List<String> parteneri = parteneriDAO.findAllParteneri();
        model.addAttribute("parteneri", parteneri);
        List<String> specializari = specializariDAO.findAllSpecilizari();
        model.addAttribute("specializari", specializari);
        return "index";
    }

    @RequestMapping(value = "/showAcorduri",method = RequestMethod.POST)
    public String showAcorduri(@ModelAttribute FormAcorduri formAcorduri, RedirectAttributes redirectAttributes) throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date date = format.parse(formAcorduri.getDataSesiune());

        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("appconfig-database.xml");
        FormAcorduriDAO formAcorduriDAO = (FormAcorduriDAO) ctx.getBean("formAcorduriDAO");

        java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());

        List<Integer> codDosar = formAcorduriDAO.findcodDosarBy(formAcorduri.getNivelStudii(),formAcorduri.getAnUniversitar(), sqlStartDate);
        Set<Integer> idAcord = formAcorduriDAO.findidAcordFromOptiuni_DosareBy(codDosar);
        List<FormAcorduriTable> formAcorduriTables = formAcorduriDAO.showFormAcorduriTable(idAcord);
        if (formAcorduriTables.size() != 0){
            redirectAttributes.addFlashAttribute("formAcorduriTables", formAcorduriTables);
        }
        else {
            redirectAttributes.addFlashAttribute("notOk",true);
        }

        System.out.println("formAcorduriTables ==== " + formAcorduriTables.size());
        return "redirect:/";
    }

    @RequestMapping(value = "/showOptiuni", method = RequestMethod.POST)
    public String showOptiuni(@ModelAttribute FormOptiuni formOptiuni, RedirectAttributes redirectAttributes) throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date date = format.parse(formOptiuni.getDataSesiunii());
        java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());

        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("appconfig-database.xml");
        FormOptiuniDAO formOptiuniDAO = (FormOptiuniDAO) ctx.getBean("formOptiuniDAO");

        String codPersoana = formOptiuniDAO.findCodPersoanaFromPersoaneBy(formOptiuni.getNume());
        Integer idStudent = formOptiuniDAO.findidStudentBy(codPersoana, formOptiuni.getDenumireSpecializare(), formOptiuni.getAnUniversitarOptiuni());
        List<Integer> codDosar = formOptiuniDAO.findcodDosarBy(idStudent, sqlStartDate);
        Set<Integer> idAcord = formOptiuniDAO.findidAcordFromOptiuni_DosareBy(codDosar);
        List<FormOptiuniTable> formOptiuniTableList = formOptiuniDAO.showFormOptiuni(idAcord);
        if (formOptiuniTableList.size() != 0){
            redirectAttributes.addFlashAttribute("formOptiuniTableList", formOptiuniTableList);
        }
        else{
            redirectAttributes.addFlashAttribute("notOkOptiuni",true);
        }
        return "redirect:/";
    }
    @RequestMapping(value = "/showProgram", method = RequestMethod.POST)
    public String showProgram(@ModelAttribute FormProgram formProgram, RedirectAttributes redirectAttributes){
        System.out.println(formProgram.getNumeProgram());
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("appconfig-database.xml");
        FormProgramDAO formProgramDAO = (FormProgramDAO) ctx.getBean("formProgramDAO");
        String codPersoana = formProgramDAO.findCodPersoanaFromPersoaneBy(formProgram.getNumeProgram());
        Set<Integer> idMentori = formProgramDAO.findMentoriForStudentBy(codPersoana);
        List<FormProgramTable> formProgramTableList = formProgramDAO.showProgram(idMentori);
        if (formProgramTableList.size() != 0){
            redirectAttributes.addFlashAttribute("formProgramTableList", formProgramTableList);
        }
        else {
            redirectAttributes.addFlashAttribute("notOkProgram", true);
        }

        return "redirect:/";
    }

    @RequestMapping(value = "/showSelectieFinala", method = RequestMethod.POST)
    public String showSelectieFinala(@ModelAttribute FormSelectie formSelectie, RedirectAttributes redirectAttributes) throws ParseException {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("appconfig-database.xml");
        FormSelectieDAO formSelectieDAO = (FormSelectieDAO) ctx.getBean("formSelectieDAO");

        String codPersoana = formSelectieDAO.findCodPersoanaFromPersoaneBy(formSelectie.getNumeStudentSelectie());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date date = format.parse(formSelectie.getDataSesiuniiSelectie());
        java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());
        Integer idStudent = formSelectieDAO.findidStudentBy(codPersoana, formSelectie.getNivelStudiiSelectie(), sqlStartDate);
        List<Integer> idOptiuniDosar = formSelectieDAO.findidOptiuniDosarBy(idStudent);
        Set<Integer> idAcord = formSelectieDAO.findidAcordBy(idOptiuniDosar);
        List<FormSelectieTable> formSelectieTableList = formSelectieDAO.showSelectieFinala(idAcord);
        if (formSelectieTableList.size() != 0){
            redirectAttributes.addFlashAttribute("formSelectieTableList", formSelectieTableList);
        }
        else {
            redirectAttributes.addFlashAttribute("notOkSelectie", true);
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/showAcorduriPrecedente", method = RequestMethod.POST)
    public String showAcorduriPrecedente(@ModelAttribute FormAcorduriPrecedente formAcorduriPrecedente, RedirectAttributes redirectAttributes){
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("appconfig-database.xml");
        FormAcorduriPrecDAO formAcorduriPrecDAO = (FormAcorduriPrecDAO) ctx.getBean("formAcorduriPrecDAO");
        String codPersoana = formAcorduriPrecDAO.findCodPersoanaFromPersoaneBy(formAcorduriPrecedente.getNumeStudentAcorduriPrecedente());
        Integer idStudent = formAcorduriPrecDAO.findidStudentBy(codPersoana, formAcorduriPrecedente.getNivelStudiiAcorduriPrecedente());
        List<Integer> idTraseuStudent = formAcorduriPrecDAO.findidTraseuStudentBy(idStudent);
        List<FormAcorduriPrecedenteTable> formAcorduriPrecedenteTableList = formAcorduriPrecDAO.showAcorduriPrecendete(idTraseuStudent);
        if (formAcorduriPrecedenteTableList.size() !=0){
            redirectAttributes.addFlashAttribute("formAcorduriPrecedenteTableList", formAcorduriPrecedenteTableList);
        }
        else {
            redirectAttributes.addFlashAttribute("notOkAcorduriPrecedente",true);
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/insertAcorduri", method = RequestMethod.POST)
    public String insertAcorduri(@ModelAttribute FormAdaugareAcorduri formAdaugareAcorduri) throws ParseException {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("appconfig-database.xml");
        AcorduriDAO acorduriDAO = (AcorduriDAO) ctx.getBean("acorduriDAO");
        acorduriDAO.insert(formAdaugareAcorduri);
        return "redirect:/";
    }

    @RequestMapping(value = "/insertParteneri", method = RequestMethod.POST)
    public String insertParteneri(@ModelAttribute FormAdaugarePartener formAdaugarePartener) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("appconfig-database.xml");
        ParteneriDAO parteneriDAO = (ParteneriDAO) ctx.getBean("parteneriDAO");
        parteneriDAO.insert(formAdaugarePartener);
        return "redirect:/";
    }

    @RequestMapping(value = "/insertOptiuniDosar", method = RequestMethod.POST)
    public String insertOptiuniDosar(@ModelAttribute FormAdaugareOptiuniDosare formAdaugareOptiuniDosare ){
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("appconfig-database.xml");
        FormAdaugareOptiuniDosareDAO formAdaugareOptiuniDosareDAO = (FormAdaugareOptiuniDosareDAO) ctx.getBean("formAdaugareOptiuniDosareDAO");
        formAdaugareOptiuniDosareDAO.insert(formAdaugareOptiuniDosare);
        return "redirect:/";
    }

    @RequestMapping(value = "/insertStudenti", method = RequestMethod.POST)
    public String insertStudenti(@ModelAttribute FormAdaugareStudent formAdaugaStudenti){
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("appconfig-database.xml");
        FormAdaugareStudentiDAO formAdaugareStudentiDAO = (FormAdaugareStudentiDAO) ctx.getBean("formAdaugareStudentiDAO");
        formAdaugareStudentiDAO.insert(formAdaugaStudenti);
        return "redirect:/";
    }
    @RequestMapping(value = "/deletePartener", method = RequestMethod.POST)
    public String deletePartener(@ModelAttribute FormStergePartener formStergePartener){
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("appconfig-database.xml");
        FormStergerePartenerDAO formStergerePartenerDAO = (FormStergerePartenerDAO) ctx.getBean("formStergerePartenerDAO");
        formStergerePartenerDAO.deletePartener(formStergePartener);
        return "redirect:/";
    }

}

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->


    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/simple-sidebar.css" />" rel="stylesheet">


</head>
<body>
<div id="wrapper">

    <!-- Sidebar menu -->
    <div id="sidebar-wrapper">
        <ul class="sidebar-nav">
            <li class="sidebar-brand">
                <a href="#" id="about">
                    Meniu
                </a>
            </li>
            <li>
                <a href="#" id="showForm7">Adaugă partener</a>
            </li>
            <li>
                <a href="#" id="showForm6">Adaugă acord</a>
            </li>
            <li>
                <a href="#" id="showForm9">Adaugă student</a>
            </li>
            <li>
                <a href="#" id="showForm8">Adaugă optiuni student</a>
            </li>
            <li>
                <a href="#" id="showForm2">Opțiuni student</a>
            </li>
            <li>
                <a href="#" id="showForm3">Selecție finală</a>
            </li>
            <li>
                <a href="#" id="showForm4">Detalii mobilități</a>
            </li>

            <li>
                <a href="#" id="showForm5">Acorduri precedente studenți</a>
            </li>
            <li>
                <a href="#" id="showForm10">Sterge partener</a>
            </li>


        </ul>
    </div>
    <!-- /#sidebar-wrapper -->

    <!-- Page Content -->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-12">
                    <div id="introLicenta">
                        <h2>Erasmus</h2>
                        <p>Gestiunea informatizată a activităților de recrutare și plasament a studenților </p>
                    </div>
                    <!--daca ii fara table e doar inputul-->
                    <div id="showForm1Content" style="display: none">
                        <form:form modelAttribute="formAcorduri" method="POST" action="showAcorduri">
                            <div class="form-group row">
                                <label class="col-sm-2 col-form-label">An Universitar</label>
                                <div class="col-sm-6">
                                    <form:input path="anUniversitar" title="Format accepat - ex:2016-2017"
                                                pattern="[0-9\-]+" type="text" class="form-control"
                                                required="required"/>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="example-date-input" class="col-2 col-form-label">Data Sesiunii</label>
                                <div class="col-3">
                                    <form:input class="form-control" path="dataSesiune" id="example-date-input"
                                                type="date" data-date="" data-date-format="dd/mm/yyyy"
                                                required="required"/>
                                </div>
                            </div>
                            <label>Nivel Studii</label>
                            <div class="form-check form-check-inline offset-sm-1">
                                <label class="form-check-label">
                                    <form:radiobutton path="nivelStudii" class="form-check-input"
                                                      name="inlineRadioOptions" id="inlineRadio1" value="licenta"/>
                                    Licenta
                                </label>
                            </div>
                            <div class="form-check form-check-inline">
                                <label class="form-check-label">
                                    <form:radiobutton path="nivelStudii" class="form-check-input"
                                                      name="inlineRadioOptions" id="inlineRadio2" value="master"/>
                                    Master
                                </label>
                            </div>
                            <div class="form-check form-check-inline ">
                                <label class="form-check-label">
                                    <form:radiobutton path="nivelStudii" class="form-check-input"
                                                      name="inlineRadioOptions" id="inlineRadio3" value="doctorat"/>
                                    Doctorat
                                </label>
                            </div>

                            <div class="form-group row">
                                <div class=" col-sm-10">
                                    <button type="submit" class="btn btn-primary">Cauta</button>
                                </div>
                            </div>
                        </form:form>
                    </div>
                    <!--care contin show ContentTable afiseaza rezulatele-->
                    <div id="showForm1ContentTable">
                        <c:choose>
                            <c:when test="${not empty formAcorduriTables}">
                                <table class="table table-sm">
                                    <thead>
                                    <tr>
                                        <th>#</th>
                                        <th>Denumire Partener</th>
                                        <th>Domeniu Activitate</th>
                                        <th>Durata Acord</th>
                                        <th>Valoare Acord</th>
                                        <th>Oras</th>
                                        <th>Tara</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:set var="count" value="1" scope="page"/>
                                    <c:forEach var="listValue" items="${formAcorduriTables}">
                                        <tr>
                                            <td>
                                                    ${count}
                                            </td>
                                            <td>
                                                    ${listValue.denumirePartener}
                                            </td>
                                            <td>
                                                    ${listValue.domeniuActivitate}
                                            </td>
                                            <td>
                                                    ${listValue.durataAcordului}
                                            </td>
                                            <td>
                                                    ${listValue.valoareaAcordlui}
                                            </td>
                                            <td>
                                                    ${listValue.oras}
                                            </td>
                                            <td>
                                                    ${listValue.tara}
                                            </td>
                                        </tr>
                                        <c:set var="count" value="${count + 1}" scope="page"/>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </c:when>
                            <c:when test="${notOk}">
                                <p style="color: red">Nu s-au gasit rezultate</p>
                            </c:when>
                        </c:choose>
                    </div>
                    <div id="showForm2Content" style="display: none">
                        <form:form modelAttribute="formOptiuni" method="POST" action="showOptiuni">
                            <div class="form-group row">
                                <label class="col-sm-2 col-form-label">Nume student</label>
                                <div class="dropdown col-sm-1">
                                    <form:select path="nume" class="btn btn-secondary dropdown-toggle">
                                        <div class="dropdown-menu">
                                            <form:options items="${studenti}" cssClass="dropdown-item"/>
                                        </div>
                                    </form:select>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-2 col-form-label">An Universitar</label>
                                <div class="col-sm-6">
                                    <form:input path="anUniversitarOptiuni" title="Format accepat - ex:2016-2017"
                                                pattern="[0-9\-]+" type="text" class="form-control"
                                                required="required"/>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-2 col-form-label">Denumire Specializare</label>
                                <div class="col-sm-6">
                                    <form:input path="denumireSpecializare" type="text" class="form-control"
                                                required="required"/>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="example-date-input" class="col-2 col-form-label">Data Sesiunii</label>
                                <div class="col-3">
                                    <form:input class="form-control" path="dataSesiunii" id="example-date-input"
                                                type="date" data-date="" data-date-format="dd/mm/yyyy"
                                                required="required"/>
                                </div>
                            </div>
                            <div class="form-group row">
                                <div class=" col-sm-10">
                                    <button type="submit" class="btn btn-primary">Cauta</button>
                                </div>
                            </div>
                        </form:form>
                    </div>
                    <div id="showForm2ContentTable">
                        <c:choose>
                            <c:when test="${not empty formOptiuniTableList}">
                                <table class="table table-sm">
                                    <thead>
                                    <tr>
                                        <th>#</th>
                                        <th>Numarul optiunii</th>
                                        <th>Domeniu Activitate</th>
                                        <th>Denumire Partener</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:set var="count" value="1" scope="page"/>
                                    <c:forEach var="listValue" items="${formOptiuniTableList}">
                                        <tr>
                                            <td>
                                                    ${count}
                                            </td>
                                            <td>
                                                    ${listValue.numarulOptiunii}
                                            </td>
                                            <td>
                                                    ${listValue.domeniuActivitate}
                                            </td>
                                            <td>
                                                    ${listValue.denumirePartener}
                                            </td>
                                        </tr>
                                        <c:set var="count" value="${count + 1}" scope="page"/>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </c:when>
                            <c:when test="${notOkOptiuni}">
                                <p style="color: red">Nu s-au gasit rezultate</p>
                            </c:when>
                        </c:choose>
                    </div>
                    <div id="showForm3Content" style="display: none">
                        <form:form modelAttribute="formSelectie" method="POST" action="showSelectieFinala">
                            <div class="form-group row">
                                <label class="col-sm-2 col-form-label">Nume student</label>
                                <div class="dropdown col-sm-1">
                                    <form:select path="numeStudentSelectie" class="btn btn-secondary dropdown-toggle">
                                        <div class="dropdown-menu">
                                            <form:options items="${studenti}" cssClass="dropdown-item"/>
                                        </div>
                                    </form:select>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="example-date-input" class="col-2 col-form-label">Data Sesiunii</label>
                                <div class="col-3">
                                    <form:input class="form-control" path="dataSesiuniiSelectie" id="example-date-input"
                                                type="date" data-date="" data-date-format="dd/mm/yyyy"
                                                required="required"/>
                                </div>
                            </div>
                            <label>Nivel Studii</label>
                            <div class="form-check form-check-inline offset-sm-1">
                                <label class="form-check-label">
                                    <form:radiobutton path="nivelStudiiSelectie" class="form-check-input"
                                                      name="inlineRadioOptions" id="inlineRadio1" value="licenta"/>
                                    Licenta
                                </label>
                            </div>
                            <div class="form-check form-check-inline">
                                <label class="form-check-label">
                                    <form:radiobutton path="nivelStudiiSelectie" class="form-check-input"
                                                      name="inlineRadioOptions" id="inlineRadio2" value="master"/>
                                    Master
                                </label>
                            </div>
                            <div class="form-check form-check-inline ">
                                <label class="form-check-label">
                                    <form:radiobutton path="nivelStudiiSelectie" class="form-check-input"
                                                      name="inlineRadioOptions" id="inlineRadio3" value="doctorat"/>
                                    Doctorat
                                </label>
                            </div>
                            <div class="form-group row">
                                <div class=" col-sm-10">
                                    <button type="submit" class="btn btn-primary">Cauta</button>
                                </div>
                            </div>
                        </form:form>
                    </div>
                    <div id="showForm3ContentTable">
                        <c:choose>
                            <c:when test="${not empty formSelectieTableList}">
                                <table class="table table-sm">
                                    <thead>
                                    <tr>
                                        <th>#</th>
                                        <th>Denumire Partener</th>
                                        <th>Domeniu Activitate</th>
                                        <th>Data plecare</th>
                                        <th>Data sosire</th>
                                        <th>Durata Acord</th>
                                        <th>Denumire comisie</th>
                                        <th>Status</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:set var="count" value="1" scope="page"/>
                                    <c:forEach var="listValue" items="${formSelectieTableList}">
                                        <tr>
                                            <td>
                                                    ${count}
                                            </td>
                                            <td>
                                                    ${listValue.denumirePartener}
                                            </td>
                                            <td>
                                                    ${listValue.domeniuActivitate}
                                            </td>
                                            <td>
                                                    ${listValue.dataPlecare}
                                            </td>
                                            <td>
                                                    ${listValue.dataSosire}
                                            </td>
                                            <td>
                                                    ${listValue.durataAcordului}
                                            </td>
                                            <td>
                                                    ${listValue.denumireComisie}
                                            </td>
                                            <td>
                                                    ${listValue.statut}
                                            </td>
                                        </tr>
                                        <c:set var="count" value="${count + 1}" scope="page"/>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </c:when>
                            <c:when test="${notOkSelectie}">
                                <p style="color: red">Nu s-au gasit rezultate</p>
                            </c:when>
                        </c:choose>
                    </div>
                    <div id="showForm4Content" style="display: none">
                        <form:form modelAttribute="formProgram" method="POST" action="showProgram">
                            <div class="form-group row">
                                <label class="col-sm-2 col-form-label">Nume student</label>
                                <div class="dropdown col-sm-1">
                                    <form:select path="numeProgram" class="btn btn-secondary dropdown-toggle">
                                        <div class="dropdown-menu">
                                            <form:options items="${studenti}" cssClass="dropdown-item"/>
                                        </div>
                                    </form:select>
                                </div>
                            </div>
                            <div class="form-group row">
                                <div class=" col-sm-10">
                                    <button type="submit" class="btn btn-primary">Cauta</button>
                                </div>
                            </div>
                        </form:form>
                    </div>
                    <div id="showForm4ContentTable">
                        <c:choose>
                            <c:when test="${not empty formProgramTableList}">
                                <table class="table table-sm">
                                    <thead>
                                    <tr>
                                        <th>#</th>
                                        <th>Denumire Activitate</th>
                                        <th>Program Activitate</th>
                                        <th>Nume mentor</th>
                                        <th>Detalii probleme</th>
                                        <th>Metoda Rezolvare</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:set var="count" value="1" scope="page"/>
                                    <c:forEach var="listValue" items="${formProgramTableList}">
                                        <tr>
                                            <td>
                                                    ${count}
                                            </td>
                                            <td>
                                                    ${listValue.denumireActivitate}
                                            </td>
                                            <td>
                                                    ${listValue.programActivitate}
                                            </td>
                                            <td>
                                                    ${listValue.numeMentor}
                                            </td>
                                            <td>
                                                    ${listValue.detaliiProblema}
                                            </td>
                                            <td>
                                                    ${listValue.metodaRezolvareProblema}
                                            </td>
                                        </tr>
                                        <c:set var="count" value="${count + 1}" scope="page"/>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </c:when>
                            <c:when test="${notOkProgram}">
                                <p style="color: red">Nu s-au gasit rezultate</p>
                            </c:when>
                        </c:choose>
                    </div>
                    <div id="showForm5Content" style="display: none">
                        <form:form modelAttribute="formAcorduriPrecedente" method="POST"
                                   action="showAcorduriPrecedente">
                            <div class="form-group row">
                                <label class="col-sm-2 col-form-label">Nume student</label>
                                <div class="dropdown col-sm-1">
                                    <form:select path="numeStudentAcorduriPrecedente"
                                                 class="btn btn-secondary dropdown-toggle">
                                        <div class="dropdown-menu">
                                            <form:options items="${studenti}" cssClass="dropdown-item"/>
                                        </div>
                                    </form:select>
                                </div>
                            </div>
                            <label>Nivel Studii</label>
                            <div class="form-check form-check-inline offset-sm-1">
                                <label class="form-check-label">
                                    <form:radiobutton path="nivelStudiiAcorduriPrecedente" class="form-check-input"
                                                      name="inlineRadioOptions" id="inlineRadio1" value="licenta"/>
                                    Licenta
                                </label>
                            </div>
                            <div class="form-check form-check-inline">
                                <label class="form-check-label">
                                    <form:radiobutton path="nivelStudiiAcorduriPrecedente" class="form-check-input"
                                                      name="inlineRadioOptions" id="inlineRadio2" value="master"/>
                                    Master
                                </label>
                            </div>
                            <div class="form-check form-check-inline ">
                                <label class="form-check-label">
                                    <form:radiobutton path="nivelStudiiAcorduriPrecedente" class="form-check-input"
                                                      name="inlineRadioOptions" id="inlineRadio3" value="doctorat"/>
                                    Doctorat
                                </label>
                            </div>
                            <div class="form-group row">
                                <div class=" col-sm-10">
                                    <button type="submit" class="btn btn-primary">Cauta</button>
                                </div>
                            </div>
                        </form:form>
                    </div>
                    <div id="showForm5ContentTable">
                        <c:choose>
                            <c:when test="${not empty formAcorduriPrecedenteTableList}">
                                <table class="table table-sm">
                                    <thead>
                                    <tr>
                                        <th>#</th>
                                        <th>An Universitar</th>
                                        <th>Denumire Partener</th>
                                        <th>Durata Acordului</th>
                                        <th>Valoarea Acordului</th>
                                        <th>Specializare</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:set var="count" value="1" scope="page"/>
                                    <c:forEach var="listValue" items="${formAcorduriPrecedenteTableList}">
                                        <tr>
                                            <td>
                                                    ${count}
                                            </td>
                                            <td>
                                                    ${listValue.anUniversitar}
                                            </td>
                                            <td>
                                                    ${listValue.denumirePartener}
                                            </td>
                                            <td>
                                                    ${listValue.durataAcordului}
                                            </td>
                                            <td>
                                                    ${listValue.valoareaAcordului}
                                            </td>
                                            <td>
                                                    ${listValue.denumireSpecializare}
                                            </td>
                                        </tr>
                                        <c:set var="count" value="${count + 1}" scope="page"/>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </c:when>
                            <c:when test="${notOkAcorduriPrecedente}">
                                <p style="color: red">Nu s-au gasit rezultate</p>
                            </c:when>
                        </c:choose>
                    </div>
                    <div id="showForm6Content" style="display: none">
                        <form:form modelAttribute="formAdaugareAcorduri" method="POST" action="insertAcorduri">
                            <div class="form-group row">
                                <label class="col-sm-2 col-form-label">Deumire partener</label>
                                <div class="dropdown col-sm-1">
                                    <form:select path="denumirePartener" class="btn btn-secondary dropdown-toggle">
                                        <div class="dropdown-menu">
                                            <form:options items="${parteneri}" cssClass="dropdown-item"/>
                                        </div>
                                    </form:select>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="example-date-input" class="col-2 col-form-label">Data Acordului</label>
                                <div class="col-3">
                                    <form:input class="form-control" path="dataAcordului" id="example-date-input"
                                                type="date" data-date="" data-date-format="dd/mm/yyyy"
                                                required="required"/>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-2 col-form-label">Valoarea acordului</label>
                                <div class="col-sm-3">
                                    <form:input path="valoareaAcordului" type="number" min="0" class="form-control"
                                                required="required"/>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="example-date-input" class="col-2 col-form-label">Data Initiala</label>
                                <div class="col-3">
                                    <form:input class="form-control" path="dataInitiala" id="example-date-input"
                                                type="date" data-date="" data-date-format="dd/mm/yyyy"
                                                required="required"/>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="example-date-input" class="col-2 col-form-label">Data Finala</label>
                                <div class="col-3">
                                    <form:input class="form-control" path="dataFinala" id="example-date-input"
                                                type="date" data-date="" data-date-format="dd/mm/yyyy"
                                                required="required"/>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-2 col-form-label">Numar locuri disponibile</label>
                                <div class="col-sm-3">
                                    <form:input path="nrLocuriDisponibile" type="number" min="0" class="form-control"
                                                required="required"/>
                                </div>
                            </div>
                            <div class="form-group row">
                                <div class=" col-sm-10">
                                    <button type="submit" class="btn btn-primary">Adauga acord</button>
                                </div>
                            </div>
                        </form:form>
                    </div>
                    <div id="showForm7Content" style="display: none">
                        <form:form modelAttribute="formAdaugareParteneri" method="POST" action="insertParteneri">
                            <div class="form-group row">
                                <label class="col-sm-2 col-form-label">Domeniu activitate</label>
                                <div class="col-sm-3">
                                    <form:input path="domeniuActivitateAdaugarePartener" type="text"
                                                class="form-control"
                                                required="required"/>
                                </div>
                            </div>

                            <div class="form-group row">
                                <label class="col-sm-2 col-form-label">Denumire partener</label>
                                <div class="col-sm-3">
                                    <form:input path="denumirePartenerAdaugarePartener" type="text" class="form-control"
                                                required="required"/>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-2 col-form-label">Tara</label>
                                <div class="col-sm-3">
                                    <form:input path="taraAdaugarePartener" type="text" class="form-control"
                                                required="required"/>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-2 col-form-label">Oras</label>
                                <div class="col-sm-3">
                                    <form:input path="orasAdaugarePartener" type="text" class="form-control"
                                                required="required"/>
                                </div>
                            </div>
                            <div class="form-group row">
                                <div class=" col-sm-10">
                                    <button type="submit" class="btn btn-primary">Adauga partener</button>
                                </div>
                            </div>
                        </form:form>
                    </div>
                    <div id="showForm8Content" style="display: none">
                        <form:form modelAttribute="formAdaugareOptiuniDosare" method="POST" action="insertOptiuniDosar">
                            <div class="form-group row">
                                <label class="col-sm-2 col-form-label">Nume student</label>
                                <div class="dropdown col-sm-1">
                                    <form:select path="numeStudentAdaugareOptiuniDosare"
                                                 class="btn btn-secondary dropdown-toggle">
                                        <div class="dropdown-menu">
                                            <form:options items="${studenti}" cssClass="dropdown-item"/>
                                        </div>
                                    </form:select>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-2 col-form-label">Denumire Partener</label>
                                <div class="dropdown col-sm-1">
                                    <form:select path="denumirePartenerAdaugareOptiuniDosare"
                                                 class="btn btn-secondary dropdown-toggle">
                                        <div class="dropdown-menu">
                                            <form:options items="${parteneri}" cssClass="dropdown-item"/>
                                        </div>
                                    </form:select>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-2 col-form-label">Numarul optiunii</label>
                                <div class="col-sm-3">
                                    <form:input path="numarulOptiuniiAdaugareOptiuniDosare" type="number" min="0"
                                                class="form-control"
                                                required="required"/>
                                </div>
                            </div>
                            <div class="form-group row">
                                <div class=" col-sm-10">
                                    <button type="submit" class="btn btn-primary">Adauga optiuni</button>
                                </div>
                            </div>
                        </form:form>
                    </div>
                    <div id="showForm9Content" style="display: none">
                        <form:form modelAttribute="formAdaugareStudenti" method="POST" action="insertStudenti">
                            <div class="form-group row">
                                <label class="col-sm-2 col-form-label">Cod persoana</label>
                                <div class="col-sm-3">
                                    <form:input path="codPersoanaAdaugareStudent" type="text" class="form-control"
                                                required="required"/>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-2 col-form-label">Nume</label>
                                <div class="col-sm-3">
                                    <form:input path="numeAdaugareStudent" type="text" class="form-control"
                                                required="required"/>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-2 col-form-label">Prenume</label>
                                <div class="col-sm-3">
                                    <form:input path="prenumeAdaugareStudent" type="text" class="form-control"
                                                required="required"/>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-2 col-form-label">Varsta</label>
                                <div class="col-sm-3">
                                    <form:input path="varstaStudentAdaugareStudent" type="number" min="0"
                                                class="form-control"
                                                required="required"/>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-2 col-form-label">Email</label>
                                <div class="col-sm-3">
                                    <form:input path="emailAdaugareStudent" type="email" class="form-control"
                                                required="required"/>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-2 col-form-label">An universitar</label>
                                <div class="col-sm-3">
                                    <form:input path="anUniversitarAdaugareStudent" type="text"
                                                title="Format accepat - ex: 2016-2017" pattern="[0-9\-]+"
                                                class="form-control"
                                                required="required"/>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-2 col-form-label">Denumire specializare</label>
                                <div class="dropdown col-sm-1">
                                    <form:select path="specializareAdaugareStudent"
                                                 class="btn btn-secondary dropdown-toggle">
                                        <div class="dropdown-menu">
                                            <form:options items="${specializari}" cssClass="dropdown-item"/>
                                        </div>
                                    </form:select>
                                </div>
                            </div>
                            <div class="form-group row">
                                <div class=" col-sm-10">
                                    <button type="submit" class="btn btn-primary">Adauga student</button>
                                </div>
                            </div>
                        </form:form>
                    </div>
                    <div id="showForm10Content" style="display: none">
                        <form:form modelAttribute="formStergePartener" method="POST" action="deletePartener">
                            <div class="form-group row">
                                <label class="col-sm-2 col-form-label">Deumire partener</label>
                                <div class="dropdown col-sm-1">
                                    <form:select path="denumirePartenerStergere" class="btn btn-secondary dropdown-toggle">
                                        <div class="dropdown-menu">
                                            <form:options items="${parteneri}" cssClass="dropdown-item"/>
                                        </div>
                                    </form:select>
                                </div>
                            </div>
                            <div class="form-group row">
                                <div class=" col-sm-10">
                                    <button type="submit" class="btn btn-primary">Sterge partener</button>
                                </div>
                            </div>
                        </form:form>
                    </div>
                    <a href="#menu-toggle" class="btn btn-outline-success" id="menu-toggle">Vezi formulare</a>
                </div>
            </div>
        </div>
    </div>
    <!-- /#page-content-wrapper -->

</div>

<!-- jQuery first, then Tether, then Bootstrap JS. -->
<script src="<c:url value="/resources/js/jquery.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
<script>
    $("#menu-toggle").click(function (e) {
        e.preventDefault();
        $("#wrapper").toggleClass("toggled");
    });
</script>
<script>
    $(document).ready(function () {
        $("#about").click(function () {
            $("#showForm1Content").hide();
            $("#showForm2Content").hide();
            $("#showForm3Content").hide();
            $("#showForm4Content").hide();
            $("#showForm5Content").hide();
            $("#showForm6Content").hide();
            $("#showForm7Content").hide();
            $("#showForm8Content").hide();
            $("#showForm9Content").hide();
            $("#showForm10Content").hide();
            $("#showForm1ContentTable").hide();
            $("#showForm2ContentTable").hide();
            $("#showForm3ContentTable").hide();
            $("#showForm4ContentTable").hide();
            $("#showForm5ContentTable").hide();
            $("#showForm6ContentTable").hide();
            $("#showForm7ContentTable").hide();
            $("#showForm8ContentTable").hide();
            $("#showForm9Content").hide();
            $("#introLicenta").show();
        });
        $("#showForm1").click(function () {
            $("#showForm2Content").hide();
            $("#showForm3Content").hide();
            $("#showForm4Content").hide();
            $("#showForm5Content").hide();
            $("#showForm6Content").hide();
            $("#showForm7Content").hide();
            $("#showForm8Content").hide();
            $("#showForm9Content").hide();
            $("#showForm10Content").hide();
            $("#showForm2ContentTable").hide();
            $("#showForm3ContentTable").hide();
            $("#showForm4ContentTable").hide();
            $("#showForm5ContentTable").hide();
            $("#showForm6ContentTable").hide();
            $("#showForm7ContentTable").hide();
            $("#showForm8ContentTable").hide();
            $("#showForm9ContentTable").hide();
            $("#introLicenta").hide();
            $("#showForm1Content").show();
        });
        $("#showForm2").click(function () {
            $("#showForm1Content").hide();
            $("#showForm3Content").hide();
            $("#showForm4Content").hide();
            $("#showForm5Content").hide();
            $("#showForm6Content").hide();
            $("#showForm7Content").hide();
            $("#showForm8Content").hide();
            $("#showForm9Content").hide();
            $("#showForm10Content").hide();
            $("#showForm1ContentTable").hide();
            $("#showForm3ContentTable").hide();
            $("#showForm4ContentTable").hide();
            $("#showForm5ContentTable").hide();
            $("#showForm6ContentTable").hide();
            $("#showForm7ContentTable").hide();
            $("#showForm8ContentTable").hide();
            $("#showForm9ContentTable").hide();
            $("#introLicenta").hide();
            $("#showForm2Content").show();
        });
        $("#showForm3").click(function () {
            $("#showForm1Content").hide();
            $("#showForm2Content").hide();
            $("#showForm4Content").hide();
            $("#showForm5Content").hide();
            $("#showForm6Content").hide();
            $("#showForm7Content").hide();
            $("#showForm8Content").hide();
            $("#showForm9Content").hide();
            $("#showForm10Content").hide();
            $("#showForm1ContentTable").hide();
            $("#showForm2ContentTable").hide();
            $("#showForm4ContentTable").hide();
            $("#showForm5ContentTable").hide();
            $("#showForm6ContentTable").hide();
            $("#showForm7ContentTable").hide();
            $("#showForm8ContentTable").hide();
            $("#showForm9ContentTable").hide();
            $("#introLicenta").hide();
            $("#showForm3Content").show();
        });
        $("#showForm4").click(function () {
            $("#showForm1Content").hide();
            $("#showForm2Content").hide();
            $("#showForm3Content").hide();
            $("#showForm5Content").hide();
            $("#showForm6Content").hide();
            $("#showForm7Content").hide();
            $("#showForm8Content").hide();
            $("#showForm9Content").hide();
            $("#showForm10Content").hide();
            $("#showForm1ContentTable").hide();
            $("#showForm2ContentTable").hide();
            $("#showForm3ContentTable").hide();
            $("#showForm5ContentTable").hide();
            $("#showForm6ContentTable").hide();
            $("#showForm7ContentTable").hide();
            $("#showForm8ContentTable").hide();
            $("#showForm9ContentTable").hide();
            $("#introLicenta").hide();
            $("#showForm4Content").show();
        });
        $("#showForm5").click(function () {
            $("#showForm1Content").hide();
            $("#showForm2Content").hide();
            $("#showForm3Content").hide();
            $("#showForm4Content").hide();
            $("#showForm6Content").hide();
            $("#showForm7Content").hide();
            $("#showForm8Content").hide();
            $("#showForm9Content").hide();
            $("#showForm10Content").hide();
            $("#showForm1ContentTable").hide();
            $("#showForm2ContentTable").hide();
            $("#showForm3ContentTable").hide();
            $("#showForm4ContentTable").hide();
            $("#showForm6ContentTable").hide();
            $("#showForm7ContentTable").hide();
            $("#showForm8ContentTable").hide();
            $("#showForm9ContentTable").hide();
            $("#introLicenta").hide();
            $("#showForm5Content").show();
        });
        $("#showForm6").click(function () {
            $("#showForm1Content").hide();
            $("#showForm2Content").hide();
            $("#showForm3Content").hide();
            $("#showForm4Content").hide();
            $("#showForm5Content").hide();
            $("#showForm7Content").hide()
            $("#showForm8Content").hide();
            $("#showForm9Content").hide();
            $("#showForm10Content").hide();
            $("#showForm1ContentTable").hide();
            $("#showForm2ContentTable").hide();
            $("#showForm3ContentTable").hide();
            $("#showForm4ContentTable").hide();
            $("#showForm5ContentTable").hide();
            $("#showForm7ContentTable").hide();
            $("#showForm8ContentTable").hide();
            $("#showForm9ContentTable").hide();
            $("#introLicenta").hide();
            $("#showForm6Content").show();
        });
        $("#showForm7").click(function () {
            $("#showForm1Content").hide();
            $("#showForm2Content").hide();
            $("#showForm3Content").hide();
            $("#showForm4Content").hide();
            $("#showForm5Content").hide();
            $("#showForm6Content").hide();
            $("#showForm8Content").hide();
            $("#showForm9Content").hide();
            $("#showForm10Content").hide();
            $("#showForm1ContentTable").hide();
            $("#showForm2ContentTable").hide();
            $("#showForm3ContentTable").hide();
            $("#showForm4ContentTable").hide();
            $("#showForm5ContentTable").hide();
            $("#showForm6ContentTable").hide();
            $("#showForm8ContentTable").hide();
            $("#showForm9ContentTable").hide();
            $("#introLicenta").hide();
            $("#showForm7Content").show();
        });
        $("#showForm8").click(function () {
            $("#showForm1Content").hide();
            $("#showForm2Content").hide();
            $("#showForm3Content").hide();
            $("#showForm4Content").hide();
            $("#showForm5Content").hide();
            $("#showForm6Content").hide();
            $("#showForm7Content").hide();
            $("#showForm9Content").hide();
            $("#showForm10Content").hide();
            $("#showForm1ContentTable").hide();
            $("#showForm2ContentTable").hide();
            $("#showForm3ContentTable").hide();
            $("#showForm4ContentTable").hide();
            $("#showForm5ContentTable").hide();
            $("#showForm6ContentTable").hide();
            $("#showForm7ContentTable").hide();
            $("#showForm9ContentTable").hide();
            $("#introLicenta").hide();
            $("#showForm8Content").show();
        });
        $("#showForm9").click(function () {
            $("#showForm1Content").hide();
            $("#showForm2Content").hide();
            $("#showForm3Content").hide();
            $("#showForm4Content").hide();
            $("#showForm5Content").hide();
            $("#showForm6Content").hide();
            $("#showForm7Content").hide();
            $("#showForm8Content").hide();
            $("#showForm10Content").hide();
            $("#showForm1ContentTable").hide();
            $("#showForm2ContentTable").hide();
            $("#showForm3ContentTable").hide();
            $("#showForm4ContentTable").hide();
            $("#showForm5ContentTable").hide();
            $("#showForm6ContentTable").hide();
            $("#showForm7ContentTable").hide();
            $("#showForm8ContentTable").hide();
            $("#introLicenta").hide();
            $("#showForm9Content").show();
        });
        $("#showForm10").click(function () {
            $("#showForm1Content").hide();
            $("#showForm2Content").hide();
            $("#showForm3Content").hide();
            $("#showForm4Content").hide();
            $("#showForm5Content").hide();
            $("#showForm6Content").hide();
            $("#showForm7Content").hide();
            $("#showForm8Content").hide();
            $("#showForm9Content").hide();
            $("#showForm1ContentTable").hide();
            $("#showForm2ContentTable").hide();
            $("#showForm3ContentTable").hide();
            $("#showForm4ContentTable").hide();
            $("#showForm5ContentTable").hide();
            $("#showForm6ContentTable").hide();
            $("#showForm7ContentTable").hide();
            $("#showForm8ContentTable").hide();
            $("#showForm9ContentTable").hide();
            $("#introLicenta").hide();
            $("#showForm10Content").show();
        });
    });
</script>
</body>
</html>
<%@ page isELIgnored = "false" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Customer</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400&display=swap" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.6.4.min.js" integrity="sha256-oP6HI9z1XaZNBrJURtCoUT5SUnxFr8s3BzRl+cbzUq8=" crossorigin="anonymous"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.13.4/css/jquery.dataTables.css" />
    <script src="https://cdn.jsdelivr.net/npm/table-to-json@1.0.0/lib/jquery.tabletojson.min.js" integrity="sha256-H8xrCe0tZFi/C2CgxkmiGksqVaxhW0PFcUKZJZo1yNU=" crossorigin="anonymous"></script>
    <script src="https://cdn.datatables.net/1.13.4/js/jquery.dataTables.js"></script>
    <script defer src="/frontend/static/js/country.js"></script>
    <script defer src="/frontend/static/js/index.js"></script>
    <script defer src="/frontend/static/js/form.js"></script>
    <script defer src="/frontend/static/js/user.js"></script>
    <script defer src="/frontend/static/js/education.js"></script>
    <style>
        body{
           font-family: 'Roboto', sans-serif !important;
        }
        .required:after{
            content:" *";
            color:red;
         }

         .hidden{
            display : hidden;
         }
    </style>
</head>
<body>
<div class="container-md">
    <form id="myform" class="row g-3 mt-2">
       <h2>Personal Information</h2>
       <div class="col-md-6">
          <label class="form-label required">User Id</label>
          <input value="${user.userId}" disabled name="userId" type="number" class="form-control" required>
       </div>
       <div class="col-md-6">
          <label for="validationServer02" class="form-label required">full name</label>
          <input value="${user.userName}" disabled type="text" class="form-control" id="validationServer02" required>
       </div>
       <div class="col-md-6">
          <label class="form-label required">Email</label>
          <input value="${user.email}" disabled name="email" type="email" class="form-control" required>
       </div>
       <div class="col-md-6">
          <label for="dateOfBirth" class="form-label required">Date Of Birth</label>
          <input name="dateOfBirth" class="form-select" id="dateOfBirth" type="date" name="begin"
             placeholder="dd-mm-yyyy"
             value="${user.dateOfBirth}" disabled
             >
       </div>
       <div class="row mt-3">
                <h3>Address Detail</h3>
       </div>

        <p class="row mt-2">
            <div class="col-9">
                <a class="btn" data-bs-toggle="collapse" href="#multiCollapseExample1" role="button" aria-expanded="true"
                   aria-controls="multiCollapseExample1">Primary Address</a>
                            <c:if test="${not empty user.secondaryAddress}">
                                            <button id="sec_button" class="btn" type="button" href="#multiCollapseExample1" data-bs-toggle="collapse" data-bs-target="#multiCollapseExample2"
                                                    aria-expanded="false" aria-controls="multiCollapseExample2">Secondary Address
                                            </button>
                            </c:if>

            </div>
        </p>

        <div class="collapse multi-collapse show" id="multiCollapseExample1">
            <div class="row">
               <div class="col-md-6">
                  <label class="form-label">Address line 2</label>
                  <input value="${user.primaryAddress.addressLine1}" disabled type="text" class="form-control">
               </div>
               <div class="col-md-6">
                  <label class="form-label">Address line 2</label>
                  <input value="${user.primaryAddress.addressLine2}" disabled type="text" class="form-control">
               </div>
               <div class="col-md-6">
                  <label class="form-label">Country</label>
                  <input value="${user.primaryAddress.country}" disabled type="text" class="form-control">
               </div>
               <div class="col-md-6">
                  <label class="form-label">State</label>
                  <input value="${user.primaryAddress.state}" disabled type="text" class="form-control">
               </div>
               <div class="col-md-6">
                  <label name="city" class="form-label">City</label>
                  <input value="${user.primaryAddress.city}" disabled type="text" class="form-control">
               </div>
               <div class="col-md-6">
                  <label class="form-label required">zip code</label>
                  <input <input value="${user.primaryAddress.zipcode}" disabled class="form-control">
               </div>
            </div>
        </div>


        <div class="collapse multi-collapse hidden" id="multiCollapseExample2">
            <div class="row">
               <div class="col-md-6">
                  <label class="form-label">Address line 2</label>
                  <input value="${user.secondaryAddress.secAddressLine1}" disabled type="text" class="form-control">
               </div>
               <div class="col-md-6">
                  <label class="form-label">Address line 2</label>
                  <input value="${user.secondaryAddress.secAddressLine2}" disabled type="text" class="form-control">
               </div>
               <div class="col-md-6">
                  <label class="form-label">Country</label>
                  <input value="${user.secondaryAddress.secCountry}" disabled type="text" class="form-control">
               </div>
               <div class="col-md-6">
                  <label class="form-label">State</label>
                  <input value="${user.secondaryAddress.secState}" disabled type="text" class="form-control">
               </div>
               <div class="col-md-6">
                  <label name="city" class="form-label">City</label>
                  <input value="${user.secondaryAddress.secCity}" disabled type="text" class="form-control">
               </div>
               <div class="col-md-6">
                  <label class="form-label required">zip code</label>
                  <input <input value="${user.secondaryAddress.secZipcode}" disabled class="form-control">
               </div>

            </div>
        </div>
       <hr>

    </form>

                  <div class="row mt-3">
                        <table id="myTable" class="display">
                            <thead>
                                <tr>
                                    <th>degreeType</th>
                                    <th>institution</th>
                                    <th>year</th>
                                    <th>percentage</th>
                                </tr>
                            </thead>
                            <tbody>
                                        <c:forEach items="${user.educationList}" var="list">
                                        <tr>
                                          <th> ${list.degreeType}</th>
                                          <th>${list.institution} </th>
                                          <th>${list.year} </th>
                                          <th>${list.percentage}</th>
                                        </tr>
                                        </c:forEach>
                            </tbody>
                        </table>

                  </div>




</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"
        crossorigin="anonymous"></script>
</body>
</html>




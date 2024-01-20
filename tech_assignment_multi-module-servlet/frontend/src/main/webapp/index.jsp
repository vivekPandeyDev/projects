<%@ taglib prefix = "print" uri = "WEB-INF/print.tld"%>
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

<print:Print message = "<h1>This is custom tag print message</h1>" />

<print:Print>
    <h2>new message</h2>
</print:Print>

<div class="container-md">
    <form id="myform" class="row g-3 mt-2">
       <h2>Personal Information</h2>
       <div class="col-md-6">
          <label for="validationServer02" class="form-label required">First name</label>
          <input  type="text" class="form-control" id="validationServer02" required>
          <div class="valid-feedback">
             Looks good!
          </div>
          <div class="invalid-feedback">
             length greater than 3 or Name Should not contain special characters!!!!
          </div>
       </div>
       <div class="col-md-6">
          <label for="validationServer03" class="form-label">LastName</label>
          <input type="text" class="form-control" id="validationServer03">
          <div class="valid-feedback">
             Looks good!
          </div>
          <div class="invalid-feedback">
             length greater than 3 or last name should not contain special characters!!!!
          </div>
       </div>
       <div class="col-md-6">
          <label for="validationServer04" class="form-label">FullName</label>
          <input name="userName" type="text" class="form-control" id="validationServer04" required>
       </div>
       <div class="col-md-6">
          <label class="form-label required">Email</label>
          <input name="email" type="email" class="form-control" required>
       </div>
       <div class="col-md-6">
          <label for="dateOfBirth" class="form-label required">Date Of Birth</label>
          <input name="dateOfBirth" class="form-select" id="dateOfBirth" type="date" name="begin"
             placeholder="dd-mm-yyyy"
             min="1997-01-01" max="2030-12-31" required>
       </div>
       <div class="row mt-3">
            <div class="col-9">
                <h3>Address Detail</h3>
            </div>
            <div class="col-3">
                <button type="button" class="btn btn-primary text-right" data-bs-toggle="modal" data-bs-target="#exampleModal">
                  Add Education Detail
                </button>
            </div>

       </div>

        <p class="row mt-2">
            <div class="col-9">
                <a id="primary_button" class="btn" data-bs-toggle="collapse" href="#multiCollapseExample1" role="button" aria-expanded="true"
                   aria-controls="multiCollapseExample1">Primary Address</a>
                <a id="sec_button" class="btn" type="button" href="#multiCollapseExample1" data-bs-toggle="collapse" data-bs-target="#multiCollapseExample2"
                        aria-expanded="false" aria-controls="multiCollapseExample2">Secondary Address
                </a>
            </div>
            <div class="col-3">
                <div class="form-check">
                    <label class="form-check-label" for="check">Use Primary address</label>
                    <input class="form-check-input" type="checkbox" name="isPrimary" id="check">
                </div>
            </div>
        </p>

        <div class="collapse multi-collapse show" id="multiCollapseExample1">
            <div class="row">
                <div class="col-md-6">
                  <label class="form-label required">Address line 1</label>
                  <input name="addressLine1" type="text" class="form-control" required>
               </div>
               <div class="col-md-6">
                  <label class="form-label">Address line 2</label>
                  <input name="addressLine2" type="text" class="form-control">
               </div>
               <div class="col-md-6">
                  <label class="form-label">Country</label>
                  <select name="country"  id="country" class="form-select" aria-label="country select" required>
                     <option value="select any">Select Country</option>
                  </select>
               </div>
               <div class="col-md-6">
                  <label class="form-label">State</label>
                  <select name="state" id="state" class="form-select" aria-label="state select" required>
                     <option value="select any">Select State</option>
                  </select>
               </div>
               <div class="col-md-6">
                  <label name="city" class="form-label">City</label>
                  <select name="city" id="city" class="form-select" aria-label="city select" required>
                     <option value="select any">Select City</option>
                  </select>
               </div>
               <div class="col-md-6">
                  <label class="form-label required">zip code</label>
                  <input name="zipcode" type="number" class="form-control" required>
               </div>
            </div>
        </div>


        <div class="collapse multi-collapse hidden" id="multiCollapseExample2">
            <div class="row">
                <div class="col-md-6">
                  <label class="form-label required">Address line 1</label>
                  <input name="secAddressLine1" type="text" class="form-control">
               </div>
               <div class="col-md-6">
                  <label class="form-label">Address line 2</label>
                  <input name="secAddressLine2" type="text" class="form-control">
               </div>
               <div class="col-md-6">
                  <label class="form-label">Country</label>
                  <select name="secCountry"  id="sec_country" class="form-select" aria-label="country select">
                     <option value="select any">Select Country</option>
                  </select>
               </div>
               <div class="col-md-6">
                  <label class="form-label">State</label>
                  <select name="secState" id="sec_state" class="form-select" aria-label="state select">
                     <option value="select any">Select State</option>
                  </select>
               </div>
               <div class="col-md-6">
                  <label name="city" class="form-label">City</label>
                  <select name="secCity" id="sec_city" class="form-select" aria-label="city select">
                     <option value="select any">Select City</option>
                  </select>
               </div>
               <div class="col-md-6">
                  <label class="form-label required">zip code</label>
                  <input name="secZipcode" type="number" class="form-control">
               </div>
            </div>
        </div>
       <hr>





       <button type="submit" class="btn btn-primary">
       Add User
       <div id="loadingDiv" class="myStyle">

           <div class="spinner-grow text-primary" role="status">
           </div>
           <div class="spinner-grow text-secondary" role="status">
           </div>
           <div class="spinner-grow text-success" role="status">
           </div>
           <div class="spinner-grow text-danger" role="status">
           </div>
           <div class="spinner-grow text-warning" role="status">
           </div>
           <div class="spinner-grow text-info" role="status">
           </div>
           <div class="spinner-grow text-light" role="status">
           </div>
           <div class="spinner-grow text-dark" role="status">
           </div>
       </div>
       </button>


    </form>


        <!-- Modal -->
        <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
          <div class="modal-dialog modal-xl">
            <div class="modal-content">
              <div class="modal-header">
                <h1 class="modal-title fs-5" id="exampleModalLabel">Education Detail </h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
              </div>

                  <div class="modal-body">
                       <form method="post" id="education" class="row">
                           <div class="col-md-3">
                              <label name="degreeType" class="form-label">City</label>
                              <select name="degreeType" id="sec_city" class="form-select" aria-label="city select">
                                 <option value="Secondary Education">Secondary Education</option>
                                 <option value="Primary Education">Primary Education</option>
                                 <option value="graduation">Graduation</option>
                                 <option value="postGraduation"> post Graduation</option>
                              </select>
                           </div>
                           <div class="col-md-3">
                              <label class="form-label">institution</label>
                              <input name="institution" type="text" class="form-control">
                           </div>
                           <div class="col-md-3">
                              <label class="form-label">year</label>
                              <input name="year" type="number" min=1947 class="form-control">
                           </div>
                           <div class="col-md-3">
                              <label class="form-label">percentage</label>
                              <input name="percentage" type="number" min=0 max=100 class="form-control">
                           </div>
                           <div class="col-md-12 mt-2">

                                <button type="submit" class="btn btn-primary">Save</button>
                                 <button id="close" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                           </div>
                       </form>
                  </div>


            </div>
          </div>
        </div>
    <hr><hr>
                  <div class="row my-3">
                        <table id="myTable" class="display">
                            <thead>
                                <tr>
                                    <th>degreeType</th>
                                    <th>institution</th>
                                    <th>year</th>
                                    <th>percentage</th>
                                </tr>
                            </thead>
                        </table>

                  </div>


</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"
        crossorigin="anonymous"></script>
</body>
</html>




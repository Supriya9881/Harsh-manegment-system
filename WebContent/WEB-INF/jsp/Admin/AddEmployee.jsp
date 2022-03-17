<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, shrink-to-fit=no">
    <title>Perclean Laundry </title>
    <link rel="icon" type="image/x-icon" href="assets/img/favicon.png"/>
    <!-- BEGIN GLOBAL MANDATORY STYLES -->
    <link href="https://fonts.googleapis.com/css?family=Nunito:400,600,700" rel="stylesheet">
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <link href="assets/css/plugins.css" rel="stylesheet" type="text/css" />
    <!-- END GLOBAL MANDATORY STYLES -->

    <!-- BEGIN PAGE LEVEL PLUGINS -->
    <link href="plugins/animate/animate.css" rel="stylesheet" type="text/css" />
    <!-- END PAGE LEVEL PLUGINS -->
    <!-- BEGIN PAGE LEVEL STYLES -->
    <link href="assets/css/tables/jquery.dataTables.min.css" rel="stylesheet" type="text/css">
    <!-- END PAGE LEVEL STYLES -->
    <!--  BEGIN CUSTOM STYLE FILE  -->
    <link href="assets/css/scrollspyNav.css" rel="stylesheet" type="text/css" />
    <link href="assets/css/components/custom-modal.css" rel="stylesheet" type="text/css" />
    <!--  END CUSTOM STYLE FILE  -->
</head>
<body>
                
                <div class="container-fluid pt-4">                                                           
                    <div class="row">
                        <div id="custom_styles" class="col-md-12 layout-spacing col-md-12">
                            <div class="statbox widget box box-shadow">
                                <div class="widget-header">
                                    <div class="row">
                                        <div class="col-xl-12 col-md-12 col-sm-12 col-12">
                                            <h4>Add Customer</h4>
                                        </div>                 
                                    </div>
                                </div>

                                <div class="widget-content widget-content-area">                                
                                    <form class="needs-validation" novalidate action="javascript:void(0);">
                                        <div class="form-row">
                                            <div class="col-mb-3 mb-2">
                                                <input type="radio" id="validationCustom04" name="gender" value="male" required>
                                                <label for="validationCustom04">Mr</label><br>
                                                <div class="valid-feedback">
                                                    Looks good!
                                                </div>
                                                <div class="invalid-feedback">
                                                    Please Select!
                                                </div>
                                            </div>   
                                            <div class="col-mb-3 mb-4">
                                                <input type="radio" id="female" name="gender" value="female">
                                                <label for="female">Mrs</label><br>
                                            </div>
                                        </div>                
                                        <div class="form-row">
                                            <div class="col-md-3 mb-4">
                                                <label for="validationCustom01">Name</label>
                                                <input type="text" class="form-control" id="validationCustom01" placeholder="First name" value="" required>
                                                <div class="valid-feedback">
                                                    Looks good!
                                                </div>
                                                <div class="invalid-feedback">
                                                    Please enter!
                                                </div>
                                            </div>
                                            <div class="col-md-3 mb-4">
                                                <label for="validationCustom02">Contact Number</label>
                                                <input type="number" class="form-control" id="validationCustom02" placeholder="Mobile Number" value="" required>
                                                <div class="valid-feedback">
                                                    Looks good!
                                                </div>
                                                <div class="invalid-feedback">
                                                    Please Enter!
                                                </div>
                                            </div>                                          
                                            <div class="col-md-3 mb-4">
                                                <label for="validationCustom03">Address</label>
                                                <input type="text" class="form-control" id="validationCustom03" placeholder="Area" required>
                                                <div class="valid-feedback">
                                                    Looks good!
                                                </div>
                                                <div class="invalid-feedback">
                                                    Please Enter!
                                                </div>
                                            </div>
                                            <div class="col-md-3 mb-4">
                                                <label for="validationCustom06">Adhaar</label>
                                                
                                                    <input type="file" class="form-control-file" id="mail_File_attachment" multiple="multiple">
                                                
                                                <div class="valid-feedback">
                                                    Looks good!
                                                </div>
                                                <div class="invalid-feedback">
                                                    Please Enter!
                                                </div>
                                            </div>                                           
                                        </div>
                                        
                                        <div class="text-right">       
                                            <button for="validationCustom05" class="form-control btn btn-primary mb-2 mr-2" type="submit" required><svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-inbox" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                                <path fill-rule="evenodd" d="M4.98 4a.5.5 0 0 0-.39.188L1.54 8H6a.5.5 0 0 1 .5.5 1.5 1.5 0 1 0 3 0A.5.5 0 0 1 10 8h4.46l-3.05-3.812A.5.5 0 0 0 11.02 4H4.98zm9.954 5H10.45a2.5 2.5 0 0 1-4.9 0H1.066l.32 2.562a.5.5 0 0 0 .497.438h12.234a.5.5 0 0 0 .496-.438L14.933 9zM3.809 3.563A1.5 1.5 0 0 1 4.981 3h6.038a1.5 1.5 0 0 1 1.172.563l3.7 4.625a.5.5 0 0 1 .105.374l-.39 3.124A1.5 1.5 0 0 1 14.117 13H1.883a1.5 1.5 0 0 1-1.489-1.314l-.39-3.124a.5.5 0 0 1 .106-.374l3.7-4.625z"/>
                                              </svg> Save Customer</button>
                                                
                                            <div class="valid-feedback">
                                                Customer Added Sucessfully
                                            </div>
                                            <div class="invalid-feedback">
                                                Please Fill All Data!
                                            </div>
                                            
                                        </div>
                                    </form>
                                    <!--  BEGIN CONTENT AREA  -->
        
                
                    
                        
                                    <div class="table pt-4">
                                        <table id="example" class="display" style="width:100%">
                                            <thead>
                                                <tr>
                                                    <th>ID</th>
                                                    <th>Name</th>
                                                    <th>Contact</th>
                                                    <th>Address</th>
                                                    <th>Payment</th>
                                                    <th>Advance</th>
                                                    <th>Order</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                
                                                <tr>
                                                    <td>1</td>
                                                    <td>Naresh</td>
                                                    <td>9766792371</td>
                                                    <td>Wadgaonsheri</td>
                                                    <td>10000</td>
                                                    <td>2000</td>
                                                    <td><a href="#"><button class="btn btn-primary btn-sm mb-2 mr-2" type="submit">View</button></a></td>
                                                </tr>
                                                <tr>
                                                    <td>2</td>
                                                    <td>Rahul</td>
                                                    <td>9503854185</td>
                                                    <td>Camp</td>
                                                    <td>10000</td>
                                                    <td>3000</td>
                                                    <td><button class="btn btn-primary btn-sm mb-2 mr-2" type="submit">View</button></td>
                                                </tr>
                                                <tr>
                                                    <td>3</td>
                                                    <td>Suresh</td>
                                                    <td>9822889032</td>
                                                    <td>Aundh</td>
                                                    <td>15000</td>
                                                    <td>5000</td>
                                                    <td><button class="btn btn-primary btn-sm mb-2 mr-2" type="submit">View</button></td>
                                                </tr>

                                            </tbody>
                                                
                                            <tfoot>
                                                <tr>
                                                    <th>ID</th>
                                                    <th>Name</th>
                                                    <th>Contact</th>
                                                    <th>Address</th>
                                                    <th>Balance Rs</th>
                                                    <th>Order</th>
                                                </tr>
                                            </tfoot>
                                        </table>
                                    </div>
                                                    

                                    
                                </div>      
                            </div>
                        </div>
                    </div>                 
                </div>
               

    
    <script>
        $(document).ready(function() {
            App.init();
        });
    </script>
    <script src="plugins/highlight/highlight.pack.js"></script>
    <script src="assets/js/custom.js"></script>
    <!-- END GLOBAL MANDATORY SCRIPTS -->
    
    <!--  BEGIN CUSTOM SCRIPTS FILE  -->
    <script src="assets/js/scrollspyNav.js"></script>
    <script src="assets/js/forms/bootstrap_validation/bs_validation_script.js"></script>
    <!--  END CUSTOM SCRIPTS FILE  -->

    <script src="assets/js/jquery.dataTables.min.js"></script>
    
    <script>
        $(document).ready(function() {
        $('#example').DataTable( {
        "scrollY": "200px" ,
        "scrollCollapse": true,
        "scrollX": true
        } );
        } );
    </script>
</body>
</html>
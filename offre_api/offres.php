<?php 
require ('dbconnexion.php'); 
$sql = "select * from offres;";
$result = mysqli_query($con,$sql);
if($result === false){
    echo mysqli_error($con);
    exit;
}else{
$offres = mysqli_fetch_all($result,MYSQLI_ASSOC);
echo json_encode($offres); 
   
}
<?php
//connexion 
require ('dbconnexion.php'); 
//variables
 
 
$candidate_id = $_POST["candidate_id"];
$offre_id = $_POST["offre_id"]; 
$etat = "en cours";   
 
$sql = "select * from postuler where candidate_id = '$candidate_id' and offre_id = '$offre_id';"; 
//execute the query 
$result = mysqli_query($con,$sql);
 
$response = array();
if ((mysqli_num_rows($result)>0))
{
	echo "Vous avez deja postuler pour cette offre";
	
}

else 
{
$sql = "insert into postuler values ('','$candidate_id','$offre_id','".$etat."');";		
	if(mysqli_query($con,$sql)){
	echo "Merci pour votre demande";
}else{
  echo mysqli_error($con);
}
}


 



?>
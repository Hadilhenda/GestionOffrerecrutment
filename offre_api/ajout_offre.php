<?php
 
require ('dbconnexion.php'); 
 
 
$titre = $_POST["titre"];
$description = $_POST["description"];
$date_debut = $_POST["date_debut"];
$date_fin = $_POST["date_fin"];
  
 

 
$sql = "insert into offres values('','".$titre."','".$description."','".$date_debut."','".$date_fin."');";
if (mysqli_query($con,$sql))
{
	echo "Données ajouté avec succes";
	
}
else
{
	echo mysqli_error($con);
}
mysqli_close($con);
?>

 
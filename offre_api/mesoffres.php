<?php
//connexion 
require ('dbconnexion.php'); 
$candidate_id = $_POST["user_id"];
 
mysqli_set_charset($con,'utf8');
$products = "select offre_id from postuler where candidate_id = '$candidate_id';";
$response = array();
if ($result1 = mysqli_query($con, $products)) {     
    while ($obj = mysqli_fetch_object($result1)) {        
		$offre_id = $obj->offre_id;		
		$sql = "select * from offres where id = $offre_id;";
		$result = mysqli_query($con,$sql);
		
	while ($row = mysqli_fetch_array($result))
{
array_push($response,array("id"=>$row["id"],"titre"=>$row["titre"],"description"=>$row["description"],
							"date_debut"=>$row["date_debut"],"date_fin"=>$row["date_fin"]));	
}

    
	
	}

 echo json_encode($response);   
mysqli_free_result($result1);
}

?>
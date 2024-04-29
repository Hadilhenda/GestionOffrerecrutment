<?php
//connexion 
require ('dbconnexion.php');  
 $offre_id = $_POST["offre_id"]; 
mysqli_set_charset($con,'utf8');
$candidates = "select candidate_id from postuler where offre_id = $offre_id ;";
$response = array();
if ($result1 = mysqli_query($con, $candidates)) {     
    while ($obj = mysqli_fetch_object($result1)) {        
		$candidate_id = $obj->candidate_id;		
		$sql = "select * from utilisateurs where id = $candidate_id;";
		$result = mysqli_query($con,$sql);
		
	while ($row = mysqli_fetch_array($result))
{
array_push($response,array("candidate_id"=>$obj->candidate_id,"nom"=>$row["nom"],"prenom"=>$row["prenom"],
							"cv"=>$row["cv"],
							"telephone"=>$row["telephone"],"email"=>$row["email"]));	
}

    
	
	}

 echo json_encode($response);   
mysqli_free_result($result1);
}

?>
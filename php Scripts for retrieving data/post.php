<?php
$servername = "127.0.0.1";
$username = "root";
$password = "";
$database = "mjunction";

// Create connection
$conn = mysqli_connect($servername, $username, $password, $database);

if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}
//$level1= "Filler-1";
//$subcat= "1";
//$Other= "";
//if( isset($_POST["json"]) ) 
//{
    //$data = json_decode($_POST[json]);
    //$data->level1= strrev($data->level1);
    //$data->subcat=strrev($data->subcat);
    //$level1 = $data->level1;
    //$subcat=$data->subcat;
    $level1= $_POST["level1"];
    $subcat=$_POST["subcat"];
    //$Other =urldecode($_POST['Other']);
    $sql="update feedback_results set Sub_Category".$subcat.' = Sub_Category'.$subcat.'+1 where Level_1="'.$level1.'"';
    $stmt = $conn->prepare($sql);
    $stmt->execute(); 
//}
?>
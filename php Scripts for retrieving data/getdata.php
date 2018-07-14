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
$heroes = array(); 
 
//this is our sql query 
$sql = "SELECT * from feedback;";
 
//creating an statment with the query
$stmt = $conn->prepare($sql);
 
//executing that statment
$stmt->execute();
 
//binding results for that statment 
$stmt->bind_result($level_1, $Sub_Category1,$Sub_Category2,$Sub_Category3,$Sub_Category4,$Sub_Category5,$Sub_Category6,$Other);
 
//looping through all the records
while($stmt->fetch()){
 
 //pushing fetched data in an array 
 $temp = [
 'level_1'=>$level_1,
 'Sub_Category1'=>$Sub_Category1,
 'Sub_Category2'=>$Sub_Category2,
 'Sub_Category3'=>$Sub_Category3,
 'Sub_Category4'=>$Sub_Category4,
 'Sub_Category5'=>$Sub_Category5,
 'Sub_Category6'=>$Sub_Category6,
 'Other'=>$Other
 ];
 
 //pushing the array inside the hero array 
 array_push($heroes, $temp);
}
 
//displaying the data in json format 
echo json_encode($heroes);
?>
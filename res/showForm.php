<?php 
if($_SERVER["REQUEST_METHOD"]=="POST"){
	include 'connection.php';
	showStudent();
}

function showStudent()
{
	global $connect;
	
	//reservasi_nama itu buat yang ingin periksa
	//pengunjung_nama itu orang yang punya akun
	$query = "SELECT p.pengunjung_nama, r.reservasi_nim, r.reservasi_nama, d.dokter_nama, r.reservasi_tanggal, r.reservasi_jam, r.keluhan FROM reservasi r INNER JOIN pengunjung p ON r.reservasi_nim = p.pengujung_nim INNER JOIN dokter d ON r.reservasi_dokter_nip = d.dokter_nip ";

	$result = mysqli_query($connect, $query);
	$number_of_rows = mysqli_num_rows($result);

	$number_array = array();

	if($number_of_rows > 0) 
	{
		while ($row = mysqli_fetch_assoc($result)) {
			$temp_array[] = $row;
		}
	}

	header('Content-Type: application/json');
	echo json_encode(array("pengunjung"=>$temp_array));
	mysqli_close($connect);
}

 ?>
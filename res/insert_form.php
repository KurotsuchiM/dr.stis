<?php 
	if ($_SERVER["REQUEST_METHOD"]=="POST")
	{
		include 'connection.php';
		createForm();
	}
	$reservasi_nim = "";
	function createform() {
		global $connect;
		print_r("expression");
		print_r($_POST['reservasi_nama']);
		if(isset($_POST['reservasi_nama']))
		{
			print_r($_POST['reservasi_nama']);
			$reservasi_nama = $_POST['reservasi_nama'];
		};
		if(isset($_POST['reservasi_nim']))
		{
			print_r($_POST['reservasi_nim']);
			$reservasi_nama = $_POST['reservasi_nim'];
		};
		if(isset($_POST['reservasi_tanggal']))
		{
			print_r($_POST['reservasi_tanggal']);
		$reservasi_tanggal = $_POST['reservasi_tanggal'];
		};
		if(isset($_POST['reservasi_jam']))
		{
			print_r($_POST['reservasi_jam']);
		$reservasi_jam = $_POST['reservasi_jam'];
		};
		if(isset($_POST['keluhan']))
		{
			print_r($_POST['keluhan']);
		$keluhan = $_POST['keluhan'];
		};
		print_r("testtttt");

		$query = " INSERT INTO reservasi(reservasi_nim,reservasi_nama,reservasi_dokter_nip,reservasi_tanggal,reservasi_jam,keluhan) VALUES ('$reservasi_nim','$reservasi_nama','19980406202102356','$reservasi_tanggal','$reservasi_jam','$keluhan'); ";

		mysqli_query($connect, $query) or die(mysqli_error($connect));

		mysqli_close($connect);
	}
/*

		$reservasi_no = 'SELECT AUTO_INCREMENT
FROM  INFORMATION_SCHEMA.TABLES
WHERE TABLE_SCHEMA = "dr.stis_cadangan"
AND   TABLE_NAME   = "reservasi"';

		$query = "INSERT INTO reservasi(reservasi_nim,reservasi_dokter_nip) SELECT p.pengunjung_nim, d.dokter_nip FROM pengunjung p,dokter d WHERE p.pengunjung_nama='$pengunjung_nama' AND d.dokter_nama='Kurotsuchi'; UPDATE reservasi SET reservasi_tanggal='$reservasi_tanggal',reservasi_jam='$reservasi_jam',keluhan='$keluhan' WHERE reservasi_no = '$reservasi_no'";

		mysqli_query($connect, $query) or die(mysqli_error($connect));

		mysqli_close($connect);
		echo($query);

	}
	*/
	
 ?>
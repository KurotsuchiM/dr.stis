-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Dec 12, 2018 at 01:41 AM
-- Server version: 10.1.37-MariaDB
-- PHP Version: 7.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dr.stis`
--

-- --------------------------------------------------------

--
-- Table structure for table `dokter`
--

CREATE TABLE `dokter` (
  `dokter_nip` varchar(18) NOT NULL,
  `dokter_nama` varchar(50) NOT NULL,
  `spesifikasi` varchar(15) NOT NULL,
  `dokter_email` varchar(50) NOT NULL,
  `dokter_alamat` varchar(100) NOT NULL,
  `dokter_notelp` varchar(15) NOT NULL,
  `password` varchar(256) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `dokter`
--

INSERT INTO `dokter` (`dokter_nip`, `dokter_nama`, `spesifikasi`, `dokter_email`, `dokter_alamat`, `dokter_notelp`, `password`) VALUES
('199705042021011935', 'Kurotsuchi<?php 
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
  
 ?> ', 'Umum', 'mayuri@gmail.com', 'kramat jati', '082280627393', '123456'),
('19980406202102356', 'Soi Fon', 'Jantung', 'fon@gmail.com', 'distrik 13', '086549643546', '123456');

-- --------------------------------------------------------

--
-- Table structure for table `pengunjung`
--

CREATE TABLE `pengunjung` (
  `pengunjung_nama` varchar(50) NOT NULL,
  `pengujung_nim` varchar(20) NOT NULL,
  `status` varchar(15) NOT NULL,
  `password` varchar(256) NOT NULL,
  `email` varchar(50) NOT NULL,
  `nomor_telpon` varchar(14) NOT NULL,
  `jenis_kelamin` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pengunjung`
--

INSERT INTO `pengunjung` (`pengunjung_nama`, `pengujung_nim`, `status`, `password`, `email`, `nomor_telpon`, `jenis_kelamin`) VALUES
('Alfian', '16.9000', 'Mahasiswa', '1213456', 'alfi@gmail.com', '081634982786', 'Laki-laki'),
('Mr. X', '19722200012414978', 'Dosen', '987654', 'suef@gmail.com', '08125487645', 'Laki-laki');

-- --------------------------------------------------------

--
-- Table structure for table `reservasi`
--

CREATE TABLE `reservasi` (
  `reservasi_no` int(11) NOT NULL,
  `reservasi_nim` varchar(20) NOT NULL,
  `reservasi_dokter_nip` varchar(50) NOT NULL,
  `reservasi_tanggal` date NOT NULL,
  `reservasi_jam` time NOT NULL,
  `keluhan` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `reservasi`
--

INSERT INTO `reservasi` (`reservasi_no`, `reservasi_nim`, `reservasi_dokter_nip`, `reservasi_tanggal`, `reservasi_jam`, `keluhan`) VALUES
(1, '16.9395', '199705042021011935', '2018-12-09', '23:00:00', 'sakit perut'),
(2, '16.9308', '199705042021011935', '2018-12-11', '06:00:00', 'maag');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `dokter`
--
ALTER TABLE `dokter`
  ADD PRIMARY KEY (`dokter_nip`);

--
-- Indexes for table `pengunjung`
--
ALTER TABLE `pengunjung`
  ADD PRIMARY KEY (`pengujung_nim`);

--
-- Indexes for table `reservasi`
--
ALTER TABLE `reservasi`
  ADD PRIMARY KEY (`reservasi_no`),
  ADD KEY `reservasi_dokter_nip` (`reservasi_dokter_nip`),
  ADD KEY `reservasi_nim` (`reservasi_nim`) USING BTREE;

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `reservasi`
--
ALTER TABLE `reservasi`
  MODIFY `reservasi_no` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `reservasi`
--
ALTER TABLE `reservasi`
  ADD CONSTRAINT `fk_reservasi_dokter_nip` FOREIGN KEY (`reservasi_dokter_nip`) REFERENCES `dokter` (`dokter_nip`),
  ADD CONSTRAINT `reservasi_ibfk_1` FOREIGN KEY (`reservasi_dokter_nip`) REFERENCES `dokter` (`dokter_nip`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

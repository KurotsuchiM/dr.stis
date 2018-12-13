-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Dec 13, 2018 at 12:52 AM
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
-- Database: `dr.stis_tes`
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
('199705042021011935', 'Kurotsuchi ', 'Umum', 'mayuri@gmail.com', 'kramat jati', '082280627393', '123456'),
('19980406202102356', 'Abu', 'Jantung', 'fon@gmail.com', 'distrik 13', '086549643546', '123456');

-- --------------------------------------------------------

--
-- Table structure for table `kunci`
--

CREATE TABLE `kunci` (
  `id` int(11) NOT NULL,
  `pengunjung_id` varchar(50) NOT NULL,
  `key` varchar(40) NOT NULL,
  `level` int(2) NOT NULL,
  `ignore_limits` tinyint(1) NOT NULL DEFAULT '0',
  `is_private_key` tinyint(1) NOT NULL DEFAULT '0',
  `alamat_ip` text,
  `tanggal_buat` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `kunci`
--

INSERT INTO `kunci` (`id`, `pengunjung_id`, `key`, `level`, `ignore_limits`, `is_private_key`, `alamat_ip`, `tanggal_buat`) VALUES
(1, '16.9395', 'CODEX@123', 0, 0, 0, NULL, '2018-10-11 13:34:33');

-- --------------------------------------------------------

--
-- Table structure for table `pengunjung`
--

CREATE TABLE `pengunjung` (
  `pengunjung_nama` varchar(50) NOT NULL,
  `pengunjung_nim` varchar(20) NOT NULL,
  `status` varchar(15) NOT NULL,
  `password` varchar(256) NOT NULL,
  `email` varchar(50) NOT NULL,
  `nomor_telpon` varchar(14) NOT NULL,
  `jenis_kelamin` varchar(10) NOT NULL,
  `tanggal_buat` datetime NOT NULL,
  `modifikasi` datetime NOT NULL,
  `status_akun` tinyint(1) NOT NULL DEFAULT '1' COMMENT '1 = Aktif | 0 = Tidak Aktif'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pengunjung`
--

INSERT INTO `pengunjung` (`pengunjung_nama`, `pengunjung_nim`, `status`, `password`, `email`, `nomor_telpon`, `jenis_kelamin`, `tanggal_buat`, `modifikasi`, `status_akun`) VALUES
('Alfian', '16.9000', 'Mahasiswa', '1213456', 'alfi@gmail.com', '081634982786', 'Laki-laki', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1),
('Anonim2', '16.9308', 'mahasiswa', 'opf324', 'min@stis.ac.id', '089569550897', 'p', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1),
('Anonim1', '16.9395', 'mahasiswa', '12f324', 'anom@stis.ac.id', '0895695505968', 'l', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1),
('Mr. X', '19722200012414978', 'Dosen', '987654', 'suef@gmail.com', '08125487645', 'Laki-laki', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1);

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
(2, '16.9308', '199705042021011935', '2018-12-11', '06:00:00', 'maag'),
(3, '', '19980406202102356', '0000-00-00', '00:00:00', ''),
(4, '', '19980406202102356', '0000-00-00', '00:00:00', ''),
(5, '', '19980406202102356', '0000-00-00', '00:00:00', ''),
(6, '', '19980406202102356', '0000-00-00', '00:00:00', '');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `dokter`
--
ALTER TABLE `dokter`
  ADD PRIMARY KEY (`dokter_nip`);

--
-- Indexes for table `kunci`
--
ALTER TABLE `kunci`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `pengunjung`
--
ALTER TABLE `pengunjung`
  ADD PRIMARY KEY (`pengunjung_nim`);

--
-- Indexes for table `reservasi`
--
ALTER TABLE `reservasi`
  ADD PRIMARY KEY (`reservasi_no`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `kunci`
--
ALTER TABLE `kunci`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `reservasi`
--
ALTER TABLE `reservasi`
  MODIFY `reservasi_no` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

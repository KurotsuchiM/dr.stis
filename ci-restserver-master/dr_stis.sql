-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Dec 14, 2018 at 03:42 AM
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
('199705042021011935', 'Kurotsuchi ', 'Umum', 'mayuri@gmail.com', 'kramat jati', '082280627393', '123456');

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
  `konfirmasi_password` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `nomor_telpon` varchar(14) NOT NULL,
  `jenis_kelamin` varchar(10) NOT NULL,
  `tanggal_buat` datetime NOT NULL,
  `modifikasi` datetime NOT NULL,
  `status_akun` tinyint(1) NOT NULL DEFAULT '1' COMMENT '1 = Aktif | 0 = Tidak Aktif',
  `loginTerakhir` timestamp NULL DEFAULT NULL,
  `loginPertama` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pengunjung`
--

INSERT INTO `pengunjung` (`pengunjung_nama`, `pengunjung_nim`, `status`, `password`, `konfirmasi_password`, `email`, `nomor_telpon`, `jenis_kelamin`, `tanggal_buat`, `modifikasi`, `status_akun`, `loginTerakhir`, `loginPertama`) VALUES
('Alfian', '16.9000', 'Mahasiswa', '1213456', '', 'alfi@gmail.com', '081634982786', 'Laki-laki', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, NULL, NULL),
('Icha', '16.9110', 'Mahasiswa', '123456Abc', '123456Abc', 'icha@stis.ac.id', '08955984858', 'perempuan', '2018-12-13 05:33:10', '2018-12-13 05:33:10', 1, NULL, NULL),
('Anonim2', '16.9308', 'mahasiswa', 'opf324', '', 'min@stis.ac.id', '089569550897', 'p', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, NULL, NULL),
('Rizki A', '16.9395', 'Mahasiswa', 'apajaBole', 'apajaBole', '16.9395@stis.ac.id', '089567867676', 'laki-laki', '0000-00-00 00:00:00', '2018-12-13 07:57:06', 1, NULL, NULL),
('Siap', '16.9990', 'Mahasiswa', 'konfirm', 'konfirm', 'siap@stis.ac.id', '089654565454', 'perempuan', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, '2018-12-14 02:14:08', '2018-12-14 01:34:58'),
('Siapa', '16.9999', 'Mahasiswa', 'konfirm', 'konfirm', 'siapa@stis.ac.id', '089654565454', 'perempuan', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, NULL, NULL),
('Mr. X', '19722200012414978', 'Dosen', '987654', '', 'suef@gmail.com', '08125487645', 'Laki-laki', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, NULL, NULL),
('Icha', '1978304928030809', 'Mahasiswa', '123456Abc', '123456Abc', 'fa@stis.ac.id', '08955984858', 'perempuan', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, NULL, NULL),
('Icha', '1978304928030830', 'Mahasiswa', '123456Abc', '123456Abc', 'ichha@stis.ac.id', '08955984858', 'perempuan', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, NULL, NULL),
('Icha', '1978304928030832', 'Mahasiswa', '123456Abc', '123456Abc', 'ia@stis.ac.id', '08955984858', 'perempuan', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, NULL, NULL),
('Icha', '1978304928030833', 'Mahasiswa', '123456Abc', '123456Abc', 'icc@stis.ac.id', '08955984858', 'perempuan', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `reservasi`
--

CREATE TABLE `reservasi` (
  `reservasi_no` int(11) NOT NULL,
  `reservasi_nim` varchar(10) NOT NULL,
  `reservasi_dokter_nip` varchar(20) NOT NULL,
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
(3, '16.9395', '199705042021011935', '2018-10-09', '10:20:00', 'sakit'),
(4, '16.9395', '199705042021011935', '2018-10-09', '10:20:00', 'sakit'),
(5, '16.9395', '199705042021011935', '2018-10-09', '10:20:00', 'sakit'),
(6, '16.9395', '199705042021011935', '2018-10-09', '10:20:00', 'sakit'),
(7, '16.9395', '199705042021011935', '2018-10-09', '10:20:00', 'sakit');

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
  MODIFY `reservasi_no` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 08 Des 2018 pada 17.21
-- Versi Server: 10.1.30-MariaDB
-- PHP Version: 7.2.1

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
-- Struktur dari tabel `dokter`
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
-- Dumping data untuk tabel `dokter`
--

INSERT INTO `dokter` (`dokter_nip`, `dokter_nama`, `spesifikasi`, `dokter_email`, `dokter_alamat`, `dokter_notelp`, `password`) VALUES
('199705042021011935', 'Kurotsuchi ', 'Umum', 'mayuri@gmail.com', 'kramat jati', '082280627393', '123456'),
('19980406202102356', 'Soi Fon', 'Jantung', 'fon@gmail.com', 'distrik 13', '086549643546', '123456');

-- --------------------------------------------------------

--
-- Struktur dari tabel `pengunjung`
--

CREATE TABLE `pengunjung` (
  `pengunjung_nama` varchar(50) NOT NULL,
  `status` varchar(15) NOT NULL,
  `password` varchar(256) NOT NULL,
  `email` varchar(50) NOT NULL,
  `nomor_telpon` varchar(14) NOT NULL,
  `jenis_kelamin` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `pengunjung`
--

INSERT INTO `pengunjung` (`pengunjung_nama`, `status`, `password`, `email`, `nomor_telpon`, `jenis_kelamin`) VALUES
('Alfian', 'Mahasiswa', '1213456', 'alfi@gmail.com', '081634982786', 'Laki-laki'),
('Mr. X', 'Dosen', '987654', 'suef@gmail.com', '08125487645', 'Laki-laki');

-- --------------------------------------------------------

--
-- Struktur dari tabel `reservasi`
--

CREATE TABLE `reservasi` (
  `reservasi_nim` varchar(10) NOT NULL,
  `reservasi_dokter_nama` varchar(50) NOT NULL,
  `reservasi_tanggal` date NOT NULL,
  `reservasi_jam` time NOT NULL,
  `keluhan` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `reservasi`
--

INSERT INTO `reservasi` (`reservasi_nim`, `reservasi_dokter_nama`, `reservasi_tanggal`, `reservasi_jam`, `keluhan`) VALUES
('16.9395', 'Abu', '2018-12-09', '23:00:00', 'sakit perut'),
('16.9308', 'Abu', '2018-12-11', '06:00:00', 'maag');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

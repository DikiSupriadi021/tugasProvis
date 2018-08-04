-- phpMyAdmin SQL Dump
-- version 4.8.2
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Aug 02, 2018 at 05:41 PM
-- Server version: 10.1.34-MariaDB
-- PHP Version: 7.2.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `kemahasiswaan_10116331_10116352`
--

-- --------------------------------------------------------

--
-- Table structure for table `pengguna`
--

CREATE TABLE `pengguna` (
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `alamat` text NOT NULL,
  `namaLengkap` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `t_mahasiswa`
--

CREATE TABLE `t_mahasiswa` (
  `nim` varchar(8) NOT NULL,
  `nama` varchar(20) NOT NULL,
  `ttl` varchar(20) NOT NULL,
  `tgl_lahir` date NOT NULL,
  `alamat` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `t_mahasiswa`
--

INSERT INTO `t_mahasiswa` (`nim`, `nama`, `ttl`, `tgl_lahir`, `alamat`) VALUES
('10116331', ' Putra Army', 'Singkawang', '1998-09-24', ' Ciumbuleuit'),
('10116333', ' Putra Army', ' Singk', '2018-07-30', ' sdafas'),
('10116344', 'dsfg', 'fdgds', '2018-07-10', 'gfds'),
('10116352', ' Diki', ' garut teluh', '1998-07-07', ' garut'),
('10116399', ' Cecep', 'Amerika Serikat', '2010-09-12', 'Mamas'),
('10116999', ' Diki', ' ad', '2018-07-13', ' da'),
('123', ' army', ' dsefgsfr', '2018-07-31', ' defgsr');

-- --------------------------------------------------------

--
-- Table structure for table `t_mata_kuliah`
--

CREATE TABLE `t_mata_kuliah` (
  `kd_mk` varchar(8) NOT NULL,
  `nama_mk` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `t_mata_kuliah`
--

INSERT INTO `t_mata_kuliah` (`kd_mk`, `nama_mk`) VALUES
('IF20001', 'Pemograman Dasar'),
('IF34348', 'Pemograman Lanjut'),
('IF37325P', 'Komputer Grafik'),
('IF99191', 'Algoritma'),
('IF99192', 'Teorema Bahasa'),
('IFProlan', 'Pemograman Lanjut');

-- --------------------------------------------------------

--
-- Table structure for table `t_nilai`
--

CREATE TABLE `t_nilai` (
  `kd_nilai` int(11) NOT NULL,
  `nim` varchar(8) NOT NULL,
  `kd_mk` varchar(8) NOT NULL,
  `absensi` int(11) NOT NULL,
  `Tgs_1` double NOT NULL,
  `Tgs_2` double NOT NULL,
  `Tgs_3` double NOT NULL,
  `UTS` double NOT NULL,
  `UAS` double NOT NULL,
  `nilai_absen` double NOT NULL,
  `nilai_tugas` double NOT NULL,
  `nilai_akhir` double NOT NULL,
  `index` char(1) NOT NULL,
  `ket` varchar(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `t_nilai`
--

INSERT INTO `t_nilai` (`kd_nilai`, `nim`, `kd_mk`, `absensi`, `Tgs_1`, `Tgs_2`, `Tgs_3`, `UTS`, `UAS`, `nilai_absen`, `nilai_tugas`, `nilai_akhir`, `index`, `ket`) VALUES
(70, '10116344', 'IF34348', 12, 65, 55, 78, 92, 45, 4.29, 16.5, 66.39, 'C', 'Lulus'),
(71, '10116344', 'IF34348', 12, 45, 78, 87, 66, 70, 4.29, 17.5, 69.59, 'B', 'Lulus'),
(72, '10116352', 'IF99192', 14, 60, 60, 60, 60, 60, 5, 15, 62, 'C', 'Lulus'),
(73, '10116352', 'IF34348', 1, 100, 100, 100, 100, 100, 0.36, 25, 95.36, 'A', 'Tidak Lulus');

-- --------------------------------------------------------

--
-- Table structure for table `t_simulasi_nilai`
--

CREATE TABLE `t_simulasi_nilai` (
  `kd_nilai` int(11) NOT NULL,
  `kd_mk` varchar(8) NOT NULL,
  `presAbsen` double NOT NULL,
  `presTugas` double NOT NULL,
  `presUTS` double NOT NULL,
  `presUAS` double NOT NULL,
  `absensi` int(11) NOT NULL,
  `Tgs_1` double NOT NULL,
  `Tgs_2` double NOT NULL,
  `Tgs_3` double NOT NULL,
  `UTS` double NOT NULL,
  `UAS` double NOT NULL,
  `nilai_absen` double NOT NULL,
  `nilai_tugas` double NOT NULL,
  `nilai_UTS` double NOT NULL,
  `nilai_UAS` double NOT NULL,
  `nilai_akhir` double NOT NULL,
  `index` char(1) NOT NULL,
  `ket` varchar(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `t_simulasi_nilai`
--

INSERT INTO `t_simulasi_nilai` (`kd_nilai`, `kd_mk`, `presAbsen`, `presTugas`, `presUTS`, `presUAS`, `absensi`, `Tgs_1`, `Tgs_2`, `Tgs_3`, `UTS`, `UAS`, `nilai_absen`, `nilai_tugas`, `nilai_UTS`, `nilai_UAS`, `nilai_akhir`, `index`, `ket`) VALUES
(1, 'IF34348', 50, 0, 50, 0, 12, 50, 50, 50, 100, 50, 42.857142857142854, 0, 25, 0, 67.85714285714286, 'E', 'Tidak Lulus'),
(2, 'IF20001', 2, 5, 5, 5, 5, 5, 4, 3, 76, 4, 0.7142857142857143, 0.18333333333333335, 0.2, 0.2, 1.2976190476190477, 'E', 'Tidak Lulus');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `pengguna`
--
ALTER TABLE `pengguna`
  ADD PRIMARY KEY (`username`);

--
-- Indexes for table `t_mahasiswa`
--
ALTER TABLE `t_mahasiswa`
  ADD PRIMARY KEY (`nim`);

--
-- Indexes for table `t_mata_kuliah`
--
ALTER TABLE `t_mata_kuliah`
  ADD PRIMARY KEY (`kd_mk`);

--
-- Indexes for table `t_nilai`
--
ALTER TABLE `t_nilai`
  ADD PRIMARY KEY (`kd_nilai`),
  ADD KEY `nim` (`nim`),
  ADD KEY `kd_mk` (`kd_mk`);

--
-- Indexes for table `t_simulasi_nilai`
--
ALTER TABLE `t_simulasi_nilai`
  ADD PRIMARY KEY (`kd_nilai`),
  ADD KEY `kd_mk` (`kd_mk`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `t_nilai`
--
ALTER TABLE `t_nilai`
  MODIFY `kd_nilai` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=74;

--
-- AUTO_INCREMENT for table `t_simulasi_nilai`
--
ALTER TABLE `t_simulasi_nilai`
  MODIFY `kd_nilai` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `t_nilai`
--
ALTER TABLE `t_nilai`
  ADD CONSTRAINT `fk_kd_mk` FOREIGN KEY (`kd_mk`) REFERENCES `t_mata_kuliah` (`kd_mk`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_nim` FOREIGN KEY (`nim`) REFERENCES `t_mahasiswa` (`nim`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

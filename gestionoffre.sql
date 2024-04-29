-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 12, 2022 at 04:51 PM
-- Server version: 10.4.17-MariaDB
-- PHP Version: 7.4.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `gestionoffre`
--

-- --------------------------------------------------------

--
-- Table structure for table `offres`
--

CREATE TABLE `offres` (
  `id` int(11) NOT NULL,
  `titre` text NOT NULL,
  `description` text NOT NULL,
  `date_debut` text NOT NULL,
  `date_fin` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `offres`
--

INSERT INTO `offres` (`id`, `titre`, `description`, `date_debut`, `date_fin`) VALUES
(1, 'developpeur web ', 'une description', '12/12/2021', '01/01/2022'),
(5, 'Developpeur web', 'une description', '18/3/2021', '26/3/2021'),
(6, 'Developpeur web', 'une description', '18/3/2021', '14/4/2021'),
(7, 'developpeur web', 'dev web desc dev web desc dev web desc dev web desc dev web desc dev web desc dev web desc dev web desc dev web desc dev web desc', '22/3/2021', '22/3/2021');

-- --------------------------------------------------------

--
-- Table structure for table `postuler`
--

CREATE TABLE `postuler` (
  `id` int(11) NOT NULL,
  `candidate_id` int(11) NOT NULL,
  `offre_id` int(11) NOT NULL,
  `etat` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `postuler`
--

INSERT INTO `postuler` (`id`, `candidate_id`, `offre_id`, `etat`) VALUES
(1, 1, 1, 'en cours'),
(2, 1, 6, 'en cours');

-- --------------------------------------------------------

--
-- Table structure for table `utilisateurs`
--

CREATE TABLE `utilisateurs` (
  `id` int(11) NOT NULL,
  `nom` text NOT NULL,
  `prenom` text NOT NULL,
  `email` text NOT NULL,
  `telephone` text NOT NULL,
  `mot_de_passe` text NOT NULL,
  `profile` text NOT NULL,
  `cv` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `utilisateurs`
--

INSERT INTO `utilisateurs` (`id`, `nom`, `prenom`, `email`, `telephone`, `mot_de_passe`, `profile`, `cv`) VALUES
(1, 'candidate', 'candidate', 'candidate@yahoo.fr', '5552145', '$2y$10$Kh7aAtNSCCvofoQ/IcGP3eXJMA3tYSYOAAy/Mtvz0IShKDbhnyZSS', 'candidate', 'some link here'),
(2, 'admin', 'admin', 'admin@yahoo.fr', '53654452', '$2y$10$Kh7aAtNSCCvofoQ/IcGP3eXJMA3tYSYOAAy/Mtvz0IShKDbhnyZSS', 'admin', 'null');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `offres`
--
ALTER TABLE `offres`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `postuler`
--
ALTER TABLE `postuler`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `utilisateurs`
--
ALTER TABLE `utilisateurs`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `offres`
--
ALTER TABLE `offres`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `postuler`
--
ALTER TABLE `postuler`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `utilisateurs`
--
ALTER TABLE `utilisateurs`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

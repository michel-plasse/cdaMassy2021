--
-- Structure de la table `role`
--

CREATE TABLE `role` (
  `id` int(11) NOT NULL,
  `libelle` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `role`
--

INSERT INTO `role` (`id`, `libelle`) VALUES
(2, 'ROLE_EMPLOYE'),
(1, 'ROLE_USER');

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `prenom` varchar(255) NOT NULL,
  `username` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id`, `nom`, `password`, `prenom`, `username`) VALUES
(1, 'user', '$2a$10$OqfX4bk002LaoS72IaTY7u6N5HW7bgC6Lx3h8lNXuTL63/rfrc4CW', 'user', 'user'),
(2, 'employe', '$2a$10$eGdgUJfIRDOMgmiIXAOivurZOSy2niN1.u99ms6c7OsLgQsWYN/Nm', 'employe', 'employe');

-- --------------------------------------------------------

--
-- Structure de la table `user_roles`
--

CREATE TABLE `user_role` (
  `users_id` int(11) NOT NULL,
  `roles_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `user_roles`
--

INSERT INTO `user_role` (`users_id`, `roles_id`) VALUES
(1, 1),
(2, 1),
(2, 2);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_mxreaibdd57kib96h1is2qfkt` (`libelle`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_jreodf78a7pl5qidfh43axdfb` (`username`);

--
-- Index pour la table `user_roles`
--
ALTER TABLE `user_role`
  ADD PRIMARY KEY (`users_id`,`roles_id`),
  ADD KEY `FKc6dfc5scokvhdj4by38b9ghvj` (`roles_id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `role`
--
ALTER TABLE `role`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `user_roles`
--
ALTER TABLE `user_role`
  ADD CONSTRAINT `FKc6dfc5scokvhdj4by38b9ghvj` FOREIGN KEY (`roles_id`) REFERENCES `role` (`id`),
  ADD CONSTRAINT `FKl8tkm649pdk9iid3u1nmw0ky0` FOREIGN KEY (`users_id`) REFERENCES `user` (`id`);
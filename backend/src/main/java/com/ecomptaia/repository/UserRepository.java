package com.ecomptaia.repository;

import com.ecomptaia.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * 👤 Repository pour la gestion des utilisateurs
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Trouve un utilisateur par son nom d'utilisateur
     */
    Optional<User> findByUsernameAndIsActiveTrue(String username);

    /**
     * Trouve un utilisateur par son email
     */
    Optional<User> findByEmailAndIsActiveTrue(String email);

    /**
     * Trouve un utilisateur par son UUID
     */
    Optional<User> findByUuidAndIsActiveTrue(UUID uuid);

    /**
     * Trouve tous les utilisateurs actifs
     */
    List<User> findByIsActiveTrue();

    /**
     * Trouve tous les utilisateurs par entreprise
     */
    List<User> findByCompanyIdAndIsActiveTrue(Long companyId);

    /**
     * Trouve tous les utilisateurs par rôle
     */
    List<User> findByRoleAndIsActiveTrue(User.Role role);

    /**
     * Vérifie si un nom d'utilisateur existe
     */
    boolean existsByUsername(String username);

    /**
     * Vérifie si un email existe
     */
    boolean existsByEmail(String email);

    /**
     * Vérifie si un UUID existe
     */
    boolean existsByUuid(UUID uuid);

    /**
     * Recherche des utilisateurs par nom (recherche partielle)
     */
    @Query("SELECT u FROM User u WHERE u.isActive = true AND (LOWER(u.firstName) LIKE LOWER(CONCAT('%', :name, '%')) OR LOWER(u.lastName) LIKE LOWER(CONCAT('%', :name, '%')) OR LOWER(u.username) LIKE LOWER(CONCAT('%', :name, '%')))")
    List<User> findByNameContainingIgnoreCase(@Param("name") String name);

    /**
     * Trouve les utilisateurs créés récemment
     */
    @Query("SELECT u FROM User u WHERE u.isActive = true AND u.createdAt >= :since")
    List<User> findRecentlyCreatedUsers(@Param("since") LocalDateTime since);

    /**
     * Trouve les utilisateurs connectés récemment
     */
    @Query("SELECT u FROM User u WHERE u.isActive = true AND u.lastLogin >= :since")
    List<User> findRecentlyLoggedInUsers(@Param("since") LocalDateTime since);

    /**
     * Compte les utilisateurs par rôle
     */
    @Query("SELECT u.role, COUNT(u) FROM User u WHERE u.isActive = true GROUP BY u.role")
    List<Object[]> countByRole();

    /**
     * Compte les utilisateurs par entreprise
     */
    @Query("SELECT u.company.id, COUNT(u) FROM User u WHERE u.isActive = true AND u.company IS NOT NULL GROUP BY u.company.id")
    List<Object[]> countByCompany();

    /**
     * Trouve les administrateurs
     */
    @Query("SELECT u FROM User u WHERE u.isActive = true AND u.role = 'ADMIN'")
    List<User> findAdmins();

    /**
     * Trouve les comptables
     */
    @Query("SELECT u FROM User u WHERE u.isActive = true AND u.role = 'ACCOUNTANT'")
    List<User> findAccountants();

    /**
     * Trouve les auditeurs
     */
    @Query("SELECT u FROM User u WHERE u.isActive = true AND u.role = 'AUDITOR'")
    List<User> findAuditors();

    /**
     * Trouve les managers
     */
    @Query("SELECT u FROM User u WHERE u.isActive = true AND u.role = 'MANAGER'")
    List<User> findManagers();

    /**
     * Trouve les utilisateurs simples
     */
    @Query("SELECT u FROM User u WHERE u.isActive = true AND u.role = 'USER'")
    List<User> findSimpleUsers();

    /**
     * Trouve les utilisateurs par prénom
     */
    List<User> findByFirstNameAndIsActiveTrue(String firstName);

    /**
     * Trouve les utilisateurs par nom de famille
     */
    List<User> findByLastNameAndIsActiveTrue(String lastName);

    /**
     * Trouve les utilisateurs par prénom et nom
     */
    List<User> findByFirstNameAndLastNameAndIsActiveTrue(String firstName, String lastName);

    /**
     * Trouve les utilisateurs connectés récemment par entreprise
     */
    @Query("SELECT u FROM User u WHERE u.isActive = true AND u.company.id = :companyId AND u.lastLogin >= :since")
    List<User> findRecentlyLoggedInUsersByCompany(@Param("companyId") Long companyId, @Param("since") LocalDateTime since);

    /**
     * Trouve les utilisateurs par rôle et entreprise
     */
    List<User> findByRoleAndCompanyIdAndIsActiveTrue(User.Role role, Long companyId);

    /**
     * Trouve les utilisateurs inactifs
     */
    List<User> findByIsActiveFalse();

    /**
     * Trouve les utilisateurs par date de création
     */
    @Query("SELECT u FROM User u WHERE u.isActive = true AND DATE(u.createdAt) = DATE(:date)")
    List<User> findByCreatedDate(@Param("date") LocalDateTime date);

    /**
     * Trouve les utilisateurs par date de dernière connexion
     */
    @Query("SELECT u FROM User u WHERE u.isActive = true AND DATE(u.lastLogin) = DATE(:date)")
    List<User> findByLastLoginDate(@Param("date") LocalDateTime date);
}

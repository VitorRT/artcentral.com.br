package br.com.artcentral.mvc.backoffice.useraccount.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.artcentral.mvc.backoffice.useraccount.entity.UserAccountEntity;

public interface UserAccountRepository extends JpaRepository<UserAccountEntity, UUID>{

}

package br.com.fiap.repository;
 

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import br.com.fiap.entity.ClienteVO;

@Transactional(rollbackFor = Exception.class)
public interface ClienteRepository extends JpaRepository<ClienteVO, Long> {

}

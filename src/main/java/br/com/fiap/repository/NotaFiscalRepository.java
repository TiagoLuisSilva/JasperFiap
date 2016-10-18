package br.com.fiap.repository;
 

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import br.com.fiap.entity.NotaFiscalVO;

@Transactional(rollbackFor = Exception.class)
public interface NotaFiscalRepository extends JpaRepository<NotaFiscalVO, Long> {

}

package dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import annotation.MyBatisRepository;
import entity.Admin;

/**
 * Mapper”≥…‰∆˜
 * 
 * @author Student
 *
 */
@Repository("adminDao")
@MyBatisRepository
public interface AdminDao {
	public void save(Admin admin);

	public List<Admin> findAll();

	public Admin findByCode(String code);

	public void modify(Admin admin);

	public void delete(String code);

}

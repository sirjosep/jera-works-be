package com.lawencon.admin.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;

import com.lawencon.admin.dao.FileDao;
import com.lawencon.admin.model.File;

@Service
public class FileService {

	private final FileDao fileDao;

	@PersistenceContext
	private EntityManager em;

	public FileService(FileDao fileDao) {
		this.fileDao = fileDao;
	}


	public File getById(String id) {
		final File file = fileDao.getById(id);
		return file;
	}

	public File insertFile(File file) {
		this.em.getTransaction().begin();
		final File files = fileDao.save(file);
		this.em.getTransaction().commit();
		return files;
	}

	public Boolean deleteById(Long id) {
		this.em.getTransaction().begin();
		final Boolean delete = fileDao.deleteById(id);
		this.em.getTransaction().commit();
		return delete;
	}

}

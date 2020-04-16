package com.itguigu.zcw.project.exception;

import com.itguigu.zcw.project.enums.ProjectExceptionEnum;

public class ProjectException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProjectException(ProjectExceptionEnum projectExceptionEnum) {
		super(projectExceptionEnum.getMessage());
		
	}
}

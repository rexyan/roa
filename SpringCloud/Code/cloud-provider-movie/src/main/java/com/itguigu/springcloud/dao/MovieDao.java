package com.itguigu.springcloud.dao;

import org.springframework.stereotype.Repository;

import com.itguigu.springcloud.bean.Movie;

@Repository
public class MovieDao {
	public Movie getMovieById(Integer id) {
		return new Movie(id, "电影名称" + id);
	}

}

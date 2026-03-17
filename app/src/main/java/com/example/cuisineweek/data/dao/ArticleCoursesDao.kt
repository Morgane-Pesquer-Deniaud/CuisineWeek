package com.example.cuisineweek.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.cuisineweek.data.entity.ArticleCourses

@Dao
interface ArticleCoursesDao {

    // ✅ On trie par nom à la place de categorie qui n'existe pas
    @Query("SELECT * FROM articles_courses ORDER BY nom ASC")
    fun getAllArticles(): LiveData<List<ArticleCourses>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(article: ArticleCourses)

    @Update
    suspend fun update(article: ArticleCourses)

    @Delete
    suspend fun delete(article: ArticleCourses)

    @Query("DELETE FROM articles_courses")
    suspend fun deleteAll()
}
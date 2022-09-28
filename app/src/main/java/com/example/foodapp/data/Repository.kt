package com.example.foodapp.data

import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

/**
 * @author : Mingaleev D
 * @data : 27/09/2022
 */
@ActivityRetainedScoped
class Repository @Inject constructor(
  remoteDataSource: RemoteDataSource,
  localDataSource: LocalDataSource
) {
  val remote = remoteDataSource
  val local = localDataSource
}
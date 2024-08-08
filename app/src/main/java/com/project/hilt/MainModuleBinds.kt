package com.project.hilt

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//A Hilt module can be thought of as a collection of “instructions” that tell Hilt how to create an instance of something that doesn’t have a constructor.
//Such as an interface or a system service.
@Module
@InstallIn(SingletonComponent::class)
abstract class MainModuleBinds {
//There are instances which can’t be constructor injected.
//Let’s list down a few such cases:
//
//Injecting interface : When a class depends on an interface, how will we tell the system to instantiate it because interfaces don’t have constructors.
//Injecting third party classes : When a class depends on an object which we don’t own because it comes from a third party library, how will we add it our Hilt tree.
//Injecting instances created by Builder pattern
//In these cases, we can provide Hilt with binding information by using Hilt modules.
//
//A Hilt module is a class that is annotated with @Module annotation. It informs Hilt how to provide instances of certain types.
//Every Hilt module must be annotated @InstallIn to tell Hilt which Android class each module will be used or installed in.

    //The @Binds annotation is used in Hilt to bind an implementation class to its corresponding interface. It is particularly useful when you have an interface and multiple implementations, and you want to specify which implementation should be used for dependency injection.
    @Binds
    @Singleton
    abstract fun provideDataRepositoryBinds(dataRepositoryImplBinds: DataRepositoryImplBinds): DataRepositoryBinds

}
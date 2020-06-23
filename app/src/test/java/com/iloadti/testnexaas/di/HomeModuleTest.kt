package com.iloadti.testnexaas.di

import com.iloadti.testnexaas.domain.repositories.SignRepository
import com.iloadti.testnexaas.domain.usecase.SignUseCase
import com.iloadti.testnexaas.presentation.main.viewmodel.SignViewModel
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.test.AutoCloseKoinTest
import org.koin.test.inject
import org.mockito.Mockito
import retrofit2.Retrofit

/*

        Nao consegui ter um tempo maior para terminar a implementacao

*/
class HomeModuleTest : AutoCloseKoinTest(){

//    @Before
//    fun setupTest(){
//
//        val appModule = module {
//            single<Retrofit> { Mockito.mock(Retrofit::class.java) }
//        }
//
//        startKoin { modules(arrayListOf(appModule, AppModule().retrofit)) }
//    }
//
//
//    @Test
//    fun `Assert if repository is provide by module`() {
//        val repository by inject<SignRepository>()
//        Assert.assertNotNull(repository)
//    }
//
//    @Test
//    fun `Assert if useCase is provide by app`() {
//        val useCase by inject<SignUseCase>()
//        Assert.assertNotNull(useCase)
//    }
//
//    @Test
//    fun `Assert if viewModel is provide by app`() {
//        val viewModel by inject<SignViewModel>()
//        with(viewModel) {
//            assertNotNull(this)
//            assertEquals(SignViewModel::class.java, javaClass)
//        }
//    }
}
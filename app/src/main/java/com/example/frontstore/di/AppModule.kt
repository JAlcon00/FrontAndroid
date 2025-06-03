package com.example.frontstore.di


import com.example.frontstore.data.remote.api.ArticuloApi
import com.example.frontstore.data.remote.api.CategoriaApi
import com.example.frontstore.data.remote.api.PedidoApi
import com.example.frontstore.data.remote.api.UsuarioApi
import com.example.frontstore.data.repository.ArticuloRepositoryImpl
import com.example.frontstore.data.repository.CategoriaRepositoryImpl
import com.example.frontstore.data.repository.PedidoRepositoryImpl
import com.example.frontstore.data.repository.UsuarioRepositoryImpl
import com.example.frontstore.domain.repository.ArticuloRepository
import com.example.frontstore.domain.repository.CategoriaRepository
import com.example.frontstore.domain.repository.PedidoRepository
import com.example.frontstore.domain.repository.UsuarioRepository
import com.example.frontstore.domain.upercase.CreatePedidoUseCase
import com.example.frontstore.domain.upercase.GetArticuloByIdUseCase
import com.example.frontstore.domain.upercase.GetArticulosPorCategoriaUseCase
import com.example.frontstore.domain.upercase.GetArticulosUseCase
import com.example.frontstore.domain.upercase.GetCategoriasUseCase
import com.example.frontstore.domain.upercase.GetPedidosPorUsuarioUseCase
import com.example.frontstore.domain.upercase.LoginUsuarioUseCase
import com.example.frontstore.domain.upercase.RegisterUsuarioUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideArticuloRepository(api: ArticuloApi): ArticuloRepository =
        ArticuloRepositoryImpl(api)

    @Provides @Singleton
    fun provideCategoriaRepository(api: CategoriaApi): CategoriaRepository =
        CategoriaRepositoryImpl(api)

    @Provides @Singleton
    fun provideUsuarioRepository(api: UsuarioApi): UsuarioRepository =
        UsuarioRepositoryImpl(api)

    @Provides @Singleton
    fun providePedidoRepository(api: PedidoApi): PedidoRepository =
        PedidoRepositoryImpl(api)

    // Use cases:
    @Provides @Singleton
    fun provideGetArticulosUseCase(repo: ArticuloRepository): GetArticulosUseCase =
        GetArticulosUseCase(repo)

    @Provides @Singleton
    fun provideGetArticuloByIdUseCase(repo: ArticuloRepository): GetArticuloByIdUseCase =
        GetArticuloByIdUseCase(repo)

    @Provides @Singleton
    fun provideGetArticulosPorCategoriaUseCase(repo: ArticuloRepository): GetArticulosPorCategoriaUseCase =
        GetArticulosPorCategoriaUseCase(repo)

    @Provides @Singleton
    fun provideGetCategoriasUseCase(repo: CategoriaRepository): GetCategoriasUseCase =
        GetCategoriasUseCase(repo)

    @Provides @Singleton
    fun provideLoginUsuarioUseCase(repo: UsuarioRepository): LoginUsuarioUseCase =
        LoginUsuarioUseCase(repo)

    @Provides @Singleton
    fun provideRegisterUsuarioUseCase(repo: UsuarioRepository): RegisterUsuarioUseCase =
        RegisterUsuarioUseCase(repo)

    @Provides @Singleton
    fun provideCreatePedidoUseCase(repo: PedidoRepository): CreatePedidoUseCase =
        CreatePedidoUseCase(repo)

    @Provides @Singleton
    fun provideGetPedidosPorUsuarioUseCase(repo: PedidoRepository): GetPedidosPorUsuarioUseCase =
        GetPedidosPorUsuarioUseCase(repo)
}

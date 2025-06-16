package com.example.frontstore.data.remote.network

import com.example.frontstore.data.remote.api.ArticuloApi
import com.example.frontstore.data.remote.api.CategoriaApi
import com.example.frontstore.data.remote.api.ClienteApi
import com.example.frontstore.data.remote.api.PedidoApi
import com.example.frontstore.data.remote.api.UsuarioApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = RetrofitClient.retrofit

    @Provides @Singleton
    fun provideArticuloApi(retrofit: Retrofit): ArticuloApi =
        retrofit.create(ArticuloApi::class.java)

    @Provides
    @Singleton
    fun provideCategoriaApi(retrofit: Retrofit): CategoriaApi =
        retrofit.create(CategoriaApi::class.java)

    @Provides @Singleton
    fun provideUsuarioApi(retrofit: Retrofit): UsuarioApi =
        retrofit.create(UsuarioApi::class.java)

    @Provides @Singleton
    fun providePedidoApi(retrofit: Retrofit): PedidoApi =
        retrofit.create(PedidoApi::class.java)

    @Provides @Singleton
    fun provideClienteApi(retrofit: Retrofit): ClienteApi =
        retrofit.create(ClienteApi::class.java)
}

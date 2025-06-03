package com.example.frontstore.data.remote.network

import androidx.test.espresso.core.internal.deps.dagger.Module
import androidx.test.espresso.core.internal.deps.dagger.Provides
import com.example.frontstore.data.remote.api.ArticuloApi
import com.example.frontstore.data.remote.api.CategoriaApi
import com.example.frontstore.data.remote.api.PedidoApi
import com.example.frontstore.data.remote.api.UsuarioApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides @Singleton
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
}

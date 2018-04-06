package com.samsung.hackerton18.teamr.belive

import android.app.Application
import android.arch.persistence.room.Room
import com.github.salomonbrys.kodein.*
import com.github.salomonbrys.kodein.android.appKodein
import com.samsung.hackerton18.teamr.belive.data.AppDatabase
import com.samsung.hackerton18.teamr.belive.web3j.KeyStore
import com.samsung.hackerton18.teamr.belive.web3j.SmartContract
import com.samsung.hackerton18.teamr.belive.web3j.Transfer

open class MyApp : Application(), KodeinAware {

    override val kodein by Kodein.lazy  {
        import(createKodein())
    }

    val appDatabase: AppDatabase by LazyKodein(appKodein).instance()

    open fun createKodein(): Kodein.Module{
        return Kodein.Module{
            bind<AppDatabase>() with singleton { Room.databaseBuilder(applicationContext, AppDatabase::class.java, "maindb3").fallbackToDestructiveMigration().build() }
            bind<KeyStore>() with singleton { KeyStore(applicationContext)}
            bind<MyManager>() with singleton { MyManager(instance(),instance())}
            bind<SmartContract>() with singleton{SmartContract(instance(),instance())}
            bind<Transfer>() with singleton{Transfer(instance(),instance())}
        }
    }
}
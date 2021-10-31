package com.kunal.flowbiztask

import android.app.Application
import com.kunal.flowbiztask.data.db.AppDatabase
import com.kunal.flowbiztask.data.network.MyApi
import com.kunal.flowbiztask.data.network.NetworkConnectionInterceptor
import com.kunal.flowbiztask.data.repositories.QuestionsRepository
import com.kunal.flowbiztask.ui.QuestionsViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class Application:Application(),KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@Application))
        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { MyApi(instance()) }
        bind() from singleton { AppDatabase (instance()) }

        //Repositories
        bind() from singleton { QuestionsRepository(instance(),instance()) }

        //VM Factory
        bind() from provider { QuestionsViewModelFactory(instance()) }

    }
}
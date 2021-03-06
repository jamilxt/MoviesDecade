package com.ahmed3elshaer.moviesdecade

import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.multidex.MultiDexApplication
import com.ahmed3elshaer.moviesdecade.di.AppTestComponent
import com.ahmed3elshaer.moviesdecade.di.DaggerAppTestComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject


class AppTest : MultiDexApplication(), HasActivityInjector, HasSupportFragmentInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentInjector

    lateinit var appComponent: AppTestComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = initDagger(this)
        appComponent.inject(this)


    }

    private fun initDagger(app: AppTest): AppTestComponent =
        DaggerAppTestComponent.builder()
            .application(app)
            .build()


}
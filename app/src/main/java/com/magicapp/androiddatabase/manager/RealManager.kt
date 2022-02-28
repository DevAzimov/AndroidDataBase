package com.magicapp.androiddatabase.manager

import com.magicapp.androiddatabase.model.Post
import io.realm.Realm
import io.realm.RealmResults

class RealmManager {
    val TAG = RealmManager::class.java.simpleName

    companion object {
        private var realmManager: RealmManager? = null
        private lateinit var realm: Realm

        val instance: RealmManager?
            get() {
                if (realmManager == null) {
                    realmManager = RealmManager()
                }
                return realmManager

            }
    }

    init {
        realm = Realm.getDefaultInstance()
    }

    fun save(post: Post?) {
        realm.beginTransaction()
        realm.copyToRealm(post!!)
        realm.commitTransaction()
    }

    fun loadPost(): ArrayList<Post> {
        val posts: ArrayList<Post> = ArrayList()
        val results: RealmResults<Post> = realm.where(Post::class.java).findAll()

        posts.addAll(results)

        return posts
    }
}


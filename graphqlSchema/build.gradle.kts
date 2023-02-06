plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.apollographql.apollo3")
}

android {
    namespace = "com.example.graphqlSchema"
    compileSdk = 33

    defaultConfig {
        minSdk = 21
        targetSdk = 33

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

//apollo {
//    service("service-a") {
//        addTypename.set("always")
//        generateApolloMetadata.set(true)
//        generateDataBuilders.set(true)
//        packageName.set("com.example.graphqlSchema.servicea")
//    }
//}

apollo {
    service("service-a") {
        generateApolloMetadata.set(true)
        sourceFolder.set("servicea")
        packageName.set("com.example.servicea")

        // The 'Date' scalar is used in feature1 and feature2, which would normally generate it once in each
        // module, which would clash. So we force generating in this module instead.
        // See https://www.apollographql.com/docs/kotlin/advanced/multi-modules/#type-clashes
        alwaysGenerateTypesMatching.set(listOf("Date"))
    }

    service("service-b") {
        generateApolloMetadata.set(true)
        sourceFolder.set("serviceb")

        packageName.set("com.example.serviceb")

        // The 'Date' scalar is used in feature1 and feature2, which would normally generate it once in each
        // module, which would clash. So we force generating in this module instead.
        // See https://www.apollographql.com/docs/kotlin/advanced/multi-modules/#type-clashes
        alwaysGenerateTypesMatching.set(listOf("Date"))
    }
}


dependencies {

    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.appcompat:appcompat:1.6.0")
    implementation("com.google.android.material:material:1.8.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    api("com.apollographql.apollo3:apollo-api")
    implementation("com.apollographql.apollo3:apollo-adapters")
    apolloUsedCoordinates(project(":feature2"))
    apolloUsedCoordinates(project(":feature1"))
}

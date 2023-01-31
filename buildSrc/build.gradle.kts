plugins {
    `kotlin-dsl`
}
repositories {
    mavenLocal()
    maven {
        setUrl("https://maven.aliyun.com/repository/public")
    }
    maven {
        setUrl("https://maven.aliyun.com/repository/google")
    }
    maven {
        setUrl("https://maven.aliyun.com/repository/jcenter")
    }
    google()
    mavenCentral()

}
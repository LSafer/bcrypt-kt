# bcrypt-kt

A simple kotlin wrapper for a bcrypt java implementation

### Install

The main way of installing this library is
using `jitpack.io`

```kts
repositories {
    // ...
    mavenCentral()
    maven { url = uri("https://jitpack.io") }
}

dependencies {
    // Replace TAG with the desired version
    implementation("net.lsafer:bcrypt-kt:TAG")
}
```

### How to use

This is just a wrapper, so the usage is almost similar:

```kt
val hash = BCryptKt.hash(thePassword)

if (BCryptKt.verify(thePassword, hash)) {
    println("Password Match")
} else {
    println("Password Mismatch")
}
```

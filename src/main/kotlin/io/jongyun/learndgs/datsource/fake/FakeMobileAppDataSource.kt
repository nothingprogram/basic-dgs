package io.jongyun.learndgs.datsource.fake

import com.github.javafaker.Faker
import com.netflix.dgs.codegen.generated.types.Address
import com.netflix.dgs.codegen.generated.types.Author
import com.netflix.dgs.codegen.generated.types.MobileApp
import org.springframework.context.annotation.Configuration
import java.util.concurrent.ThreadLocalRandom
import javax.annotation.PostConstruct

@Configuration
class FakeMobileAppDataSource(
    val faker: Faker
) {

    companion object {
        val MOBILE_APP_LIST = ArrayList<MobileApp>()
    }

    @PostConstruct
    fun postConstruct() {
        (0..20).forEach() {
            val addresses = ArrayList<Address>()
            val author = Author(
                addresses = addresses,
                originCountry = faker.country().name()
            )
            val mobileApp = MobileApp(
                name = faker.app().name(),
                author = author,
                version = faker.app().version(),
                platform = randomMobileAppPlatform(),
            )

            (0..ThreadLocalRandom.current().nextInt(1, 3)).forEach() {
                val address = Address(
                    country = faker.address().country(),
                    city = faker.address().city(),
                    street = faker.address().streetName(),
                    zipCode = faker.address().zipCode()
                )
                addresses.add(address)
            }
            MOBILE_APP_LIST.add(mobileApp)
        }
    }

    private fun randomMobileAppPlatform(): List<String> {
        return when (ThreadLocalRandom.current().nextInt(10) % 3) {
            0 -> listOf("ios")
            1 -> listOf("android")
            else -> listOf("ios", "andriod")
        }
    }
}
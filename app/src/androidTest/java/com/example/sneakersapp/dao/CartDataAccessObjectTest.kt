package com.example.sneakersapp.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.example.sneakersapp.mock.SneakerDetailMock
import com.example.sneakersapp.testUtils.getOrAwaitValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.*
import javax.inject.Inject

@SmallTest
@ExperimentalCoroutinesApi
@HiltAndroidTest
class CartDataAccessObjectTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var database : CartDataBase

    private lateinit var dao : CartDataAccessObject

    @Before
    fun setup() {

        hiltRule.inject()
        dao = database.getCartDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun verifyUpsertDataAndGet(): Unit = runBlocking {
        val sneaker = SneakerDetailMock.getSneaker(SneakerDetailMock.ID)

        dao.upsert(sneaker)
        val getAllSneakers = dao.getAllItem().getOrAwaitValue()
        sneakerAssertions(getAllSneakers)

        dao.delete(sneaker)
    }

    @Test
    fun verifyAfterDeleting() = runBlocking {
        val sneaker = SneakerDetailMock.getSneaker(SneakerDetailMock.ID)

        dao.upsert(sneaker)
        dao.delete(sneaker)

        val getAllSneakers = dao.getAllItem().getOrAwaitValue()
        TestCase.assertEquals(0, getAllSneakers.size)
    }

    @Test
    fun verifyTotalPriceOfCartItem() = runBlocking {
        val sneaker = SneakerDetailMock.getSneaker(SneakerDetailMock.ID)

        dao.upsert(sneaker)

        val getAllSneakers = dao.totalPriceOfCartItem()
        TestCase.assertEquals(SneakerDetailMock.PRICE_OF_SNEAKER, getAllSneakers)
        dao.delete(sneaker)
    }

    private fun sneakerAssertions(sneaker: List<SneakerTable>) {

        TestCase.assertEquals(1, sneaker.size)

        // verify article fields
        Assert.assertEquals(
            SneakerDetailMock.MOCK_CONTENT,
            sneaker[0].brandName
        )
        Assert.assertEquals(
            SneakerDetailMock.ID,
            sneaker[0].id
        )
        Assert.assertEquals(
            SneakerDetailMock.PRICE_OF_SNEAKER,
            sneaker[0].price
        )
        Assert.assertEquals(
            SneakerDetailMock.SNEAKER_SIZE,
            sneaker[0].size
        )
        Assert.assertEquals(
            SneakerDetailMock.SNEAKER_COLOR,
            sneaker[0].color
        )
        Assert.assertEquals(
            SneakerDetailMock.IMAGE_URL,
            sneaker[0].gridPictureUrl
        )
    }
}
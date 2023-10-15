package com.example.ticketingappcompose.filter.custom_filter

import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ticketingappcompose.filter.components.FilterHeaderWithBackArrow
import com.example.ticketingappcompose.filter.components.FilterSubHeaderComponent
import com.example.ticketingappcompose.filter.components.GenericListItems
import com.example.ticketingappcompose.filter.components.SaveFilterButtonComponent
import com.example.ticketingappcompose.filter.components.SearchComponent
import com.example.ticketingappcompose.filter.components.SelectAllButtonComponent
import com.example.ticketingappcompose.filter.custom_filter.CustomDialogViewModel.Companion.HOURS_OF_DAY
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch





@OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalFoundationApi::class
)

/**
 * this class handles HOURS_OF_DAY, VENUE AND PERFORMER bottom dialog filter,
 * @param filterType represents the kind of filter,  which takes companion object [HOURS_OF_DAY, or VENUE or PERFORMER]
 * @param viewModel this is the view model class
 */
@Composable
fun CustomFilterDialog(
    filterType: String = HOURS_OF_DAY,
    viewModel: CustomDialogViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    onDismiss: () -> Unit,
    onResetClick: () -> Unit,
    onPrimaryButtonClick: () -> Unit
) {

    BottomSheetScaffold(
        sheetContainerColor = Color.White,
        sheetContent = {
            Column(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(vertical = 8.dp, horizontal = 8.dp)
            ) {

                viewModel.initFilterType(filterType)

                val coroutineScope = rememberCoroutineScope()
                val pagerState = rememberPagerState(initialPage = 0) { 3 }
                var hasSelectedAll = if(pagerState.currentPage == 1 ) viewModel.shouldSelectAllRecommendedButtonPressed else viewModel.shouldSelectAllButtonPressed

                val filterItems: MutableList<PerformerAndVenueFilterType> = viewModel.allFilterItem
                val recommendedFilterItem: MutableList<PerformerAndVenueFilterType> = viewModel.recommendedFilterItem
                var searchText by remember { mutableStateOf("") }


                FilterHeaderWithBackArrow(headerText = filterType) { onDismiss() }

                Spacer(modifier = Modifier.height(16.dp))

                if (filterType == HOURS_OF_DAY) {
                    SelectAllButtonComponent(hasSelectedAll = hasSelectedAll.value) {
                        viewModel.toggleAllItems(pagerState.currentPage)
                    }
                } else {
                    SearchComponent(modifier = Modifier, onValueChanged = { searchInput ->
                        searchText = searchInput
                    })
                }



                Spacer(modifier = Modifier.height(8.dp))

                FilterSubHeaderComponent(text = "Select $filterType")

                Spacer(modifier = Modifier.height(20.dp))

                //Tab Row
                TabRow(
                    contentColor = Color.Transparent.copy(0.1f),
                    modifier = Modifier,
                    selectedTabIndex = pagerState.currentPage,
                    containerColor = Color.White,
                    indicator = { tabPositions ->

                        //tab row inddicator with animation
                        val transition =
                            updateTransition(targetState = pagerState.currentPage, label = "")
                        val indicatorStart by transition.animateDp(
                            transitionSpec = {
                                if (initialState < targetState) {
                                    spring(dampingRatio = 1f, stiffness = 50f)//Using spring
                                } else {
                                    spring(
                                        dampingRatio = 1f,
                                        stiffness = 100f
                                    )//Change stiffness according to your need
                                }
                            }, label = ""
                        ) {
                            tabPositions[it].left
                        }

                        val indicatorEnd by transition.animateDp(//Indicator end transition animation
                            transitionSpec = {
                                if (initialState < targetState) {
                                    spring(
                                        dampingRatio = 1f,
                                        stiffness = 100f
                                    )//Or you can change your anim here
                                } else {
                                    spring(dampingRatio = 1f, stiffness = 50f)
                                }
                            }, label = ""
                        ) {
                            tabPositions[it].right
                        }

                        Box(
                            modifier = Modifier
                                .offset(x = indicatorStart)
                                .wrapContentSize(align = Alignment.BottomStart)
                                .width(indicatorEnd - indicatorStart)
                                .height(2.dp)
                                .border(2.dp, Color(0xFF098EEF))
                        ) {

                        }
                    }) {
                    HourOfDayTab(modifier = Modifier, coroutineScope = coroutineScope, pagerState = pagerState)
                }

                //Method contains
                PerformerHorizontalPager(
                    modifier = Modifier,
                    viewModel = viewModel,
                    searchText = searchText,
                    pagerState = pagerState,
                    filterItems = viewModel.allFilterItem,
                    recommendedFilterItem = viewModel.recommendedFilterItem,
                    currentPage = pagerState.currentPage,
                    selectedItems = viewModel.selectedFilterItem,
                    onItemSelected = { index, isRecommended ->
                        if (isRecommended)
                            viewModel.addItem(
                                recommendedFilterItem[index]
                            )
                        else viewModel.addItem(
                            filterItems[index]
                        )
                    },
                    onItemRemoved = { index, isRecommended ->

                        if (isRecommended) {
                            viewModel.removeItem(
                                recommendedFilterItem[index]
                            )
                        } else {
                            viewModel.removeItem(
                                filterItems[index]
                            )
                        }
                    }
                )


                SaveFilterButtonComponent(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    onPrimaryButtonClick = { onPrimaryButtonClick() })

                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    ) {

    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PerformerHorizontalPager(
    modifier: Modifier,
    viewModel: CustomDialogViewModel,
    searchText: String,
    pagerState: PagerState,
    currentPage :Int,
    filterItems: MutableList<PerformerAndVenueFilterType>,
    recommendedFilterItem: MutableList<PerformerAndVenueFilterType>,
    selectedItems: MutableList<PerformerAndVenueFilterType>,
    onItemSelected: (Int, Boolean) -> Unit,
    onItemRemoved: (Int, Boolean) -> Unit
) {


    HorizontalPager(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .fillMaxWidth(),
        state = pagerState,
        pageSpacing = 0.dp,
        userScrollEnabled = true,
        reverseLayout = false,
        contentPadding = PaddingValues(0.dp),

        pageContent = {
            when (pagerState.currentPage) {
                //handled all selected hours tab

                0 -> {
                    PerformerHorizontalPagerItem(
                        viewModel = viewModel,
                        searchText = searchText,
                        itemCount = selectedItems.size,
                        filterItems = selectedItems,
                        currentPage = currentPage,
                        onItemSelected = {
                            //onItemSelected(index)
                        },
                        onItemRemoved = {
                            //onItemRemoved(index)
                        })
                }

                //handled all recommended hours tab
                1 -> {
                    PerformerHorizontalPagerItem(
                        viewModel = viewModel,
                        searchText = searchText,
                        itemCount = recommendedFilterItem.size,
                        filterItems = recommendedFilterItem,
                        currentPage = currentPage,
                        onItemSelected = { index ->
                            onItemSelected(index, true)
                        },
                        onItemRemoved = { index ->
                            onItemRemoved(index, true)
                        })

                }
                //handled all hours tab
                2 -> {
                    PerformerHorizontalPagerItem(
                        viewModel = viewModel,
                        searchText = searchText,
                        itemCount = filterItems.size,
                        filterItems = filterItems,
                        currentPage = currentPage,
                        onItemSelected = { index ->
                            onItemSelected(index, false)
                        },
                        onItemRemoved = { index ->
                            onItemRemoved(index, false)
                        })
                }

            }
        }
    )
}


@Composable
fun PerformerHorizontalPagerItem(
    viewModel: CustomDialogViewModel,
    searchText: String,
    itemCount: Int = 0,
    currentPage: Int,
    filterItems: MutableList<PerformerAndVenueFilterType>,
    onItemSelected: (Int) -> Unit,
    onItemRemoved: (Int) -> Unit
) {
    Column(modifier = Modifier.height(300.dp)) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            contentPadding = PaddingValues(
                vertical = 10.dp,
                horizontal = 4.dp
            )

        ) {
            var items: List<PerformerAndVenueFilterType> = filterItems
            var mItemCount = filterItems.size

            if (searchText.isNotEmpty()) {
                items = filterItems.filter {
                    it.filterTypeName.contains(searchText, ignoreCase = true)
                }
                mItemCount = items.size
            }

            items(mItemCount) { index ->
                GenericListItems(
                    modifier =Modifier,
                    itemIndex = index,
                    viewModel2 = viewModel,
                    currentPage = currentPage,
                    item = items[index],
                    isItemSelected = items[index].isSelected,
                    onItemSelected = { isSelected ->
                        if (isSelected) {
                            onItemSelected(index)
                        } else {
                            onItemRemoved(index)
                        }
                    }
                )
            }
        }
    }
}


object TabItems {
    val allTabItems = listOf(
        TabItem(
            icon = Icons.Default.Check,
            text = "Selected",
            screen = {

            }
        ),
        TabItem(
            icon = Icons.Default.ThumbUp,
            text = "Recommended",
            screen = {
                //EmptyComingSoon(modifier = Modifier)
            }
        ),
        TabItem(
            icon = Icons.Default.Dashboard,
            text = "All",
            screen = {
                //EmptyComingSoon(modifier = Modifier)
            }
        )

    )
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HourOfDayTab(
    modifier: Modifier,
    coroutineScope: CoroutineScope,
    pagerState: PagerState
) {
    TabItems.allTabItems.forEachIndexed { index, tabItem ->
        Tab(
            text = {
                val selectedColor =
                    if (pagerState.currentPage == index) Color.Black else Color.LightGray
                TabRowItemContent(
                    modifier = modifier,
                    selectedColor = selectedColor,
                    tabItem = tabItem
                )
            },

            selected = pagerState.currentPage == index,

            onClick = {
                coroutineScope.launch {
                    pagerState.animateScrollToPage(index)
                }
            })
    }
}


@Composable
fun TabRowItemContent(
    modifier: Modifier,
    selectedColor: Color,
    tabItem: TabItem
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {

        Image(
            modifier = Modifier.size(16.dp),
            imageVector = tabItem.icon,
            contentDescription = tabItem.text,
            colorFilter = ColorFilter.tint(
                color = selectedColor
            )
        )
        Spacer(modifier = Modifier.width(2.dp))

        Text(
            text = tabItem.text,
            maxLines = 1,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                color = selectedColor
            )
        )
    }
}


data class TabItem(
    val icon: ImageVector,
    val text: String,
    val screen: @Composable () -> Unit
)


@Preview
@Composable
fun PerformerFilterDialogPreview() {
    CustomFilterDialog(
        onDismiss = { },
        onResetClick = { }) {

    }
}




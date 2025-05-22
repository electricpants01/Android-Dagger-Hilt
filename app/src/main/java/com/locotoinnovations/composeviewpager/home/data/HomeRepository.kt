package com.locotoinnovations.composeviewpager.home.data

import javax.inject.Inject

class HomeRepository @Inject constructor(
    homeService: IHomeService,
) : IHomeService by homeService
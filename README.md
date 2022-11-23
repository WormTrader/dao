# dao

The package `com.wormtrader.dao` holds *data access objects*
 used by the WormTrader applications to simplify using trading
 related pseudo data types. to saved and retrieved from the database as well as
 rendered to the UI to be edited by the user.

These *DAO*'s can be rendered to the UI to be edited by the user, and then
 saved and retrieved from the database.

For instance,  represent a trade execution (i.e. the purchase or sale of stocks or options)
 we use a `Qty` which shows positive quantities (purchases) on a blue background
 and negative quantities (sales) on a red background. Similarly the `USD` pseudo type
 (US Dollars) uses red and blue for positive and neqative amounts.
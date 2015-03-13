# Please, no sleep.

Prevent your heroku apps from sleeping... by yourself !

## Why ?



## How to use ?

Clone this repo :

`git clone git@github.com:Cowa/PleaseNoSleep.git`

Obviously, you should have the [heroku toolbelt](https://toolbelt.heroku.com/).

Create a new heroku app :

`heroku create`

Set heroku app's URL you want to keep awake :

`heroku config:set URL_TO_PING=http://foo.com,http://bar.com`

URL must be separated with comma `,`

**Important**, you also have to add your PleaseNoSleep app URL to `URL_TO_PING`, so it will keep itself awake.

For example, at the [CatFactory team](), we have this :

`URL_TO_PING=http://catfactory-api.herokuapp.com,http://catfactory.herokuapp.com,http://serene-dusk-6746.herokuapp.com`

Where `http://serene-dusk-6746.herokuapp.com` is the PleaseNoSleep app.

And finally, deploy it :

`git push heroku master`

Hooray ! Your heroku apps will no longer fall asleep **:)**

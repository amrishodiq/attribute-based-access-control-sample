# README


## Database Preparation
1. Install MySql database server into your computer, and start the server. 
2. Create username and password to be used by the server app. In this example, we use username `root` and password `12345678`.
3. Login into MySql using this username and password.
4. Create a schema named `rest`. See [01-create-schema.sql](SQL/01-create-schema.sql).
5. Create required tables. See [02-create-tables.sql](SQL/02-create-tables.sql).
6. Add dummy data. See [03-add-dummy-data.sql](SQL/03-add-dummy-data.sql).
7. Create table for requestors. See [04-create-table-requestors.sql](SQL/04-create-table-requestors.sql).
8. Add dummy requestors data. See [05-add-dummy-requestor.sql](SQL/05-add-dummy-requestor.sql).

The latest 2 items (number 7 and 8) is required to demonstrate how authentication works.

## Try The App
[Authorization](./Authorization/) directory is a Netbeans Maven Project that contains 2 elements:

1. A REST API as the server
2. A web based app as the client

Follow these steps to try the app
1. Install Netbeans, then open it.

2. From Netbeans, open the Authorization project.

3. In the panel on the left, right click on the project name (Authorization), then press `Build` from the menu. You need an internet connection because Netbeans will use Maven to download some libraries.

4. Wait until the Build process is done.

5. Edit [config.yml](./Authorization/config.yml) based on your username and password from Database Preparation above.

6. Open command prompt or console, navigate to `Authorization` directory. Then execute the following command:
   ```
   java -jar target/Authorization-1.0.jar server config.yml
   ```
   This step will start a web server listening to port 8080 (it's default port).

7. Ensure your database is up and running, and have the `rest` schema.

8. Open web browser and navigate to `http://localhost:8080/cms`. You will see a login page. Input valid username and password (based on our previously made **dummy requestor data**, for example: username `reisa.broto` with password `12345678`). 

9. The browser will send HTTP POST request to server (which you can see via `Inspect Element`). If the username and password is match, then the view will be changed with a table have title `Patient Real Name`. First, there are one row below this title with text `Fetching ...`. After a few seconds, this `Fetching ...` will be replaced with three rows, each contains the dummy data which we add at Database Preparation.

## Understand What Happened Behind The Schene
1. See the browser, the tab with address bar pointing to `http://localhost:8080/cms/index.html`.
2. Open `Inspect Element` by right click on the page, then press menu `Inspect`. I assumed you will use Google Chrome to try this.
3. Press tab Network, you will see the request sent from the browser to the server, as well ass what server response to the request.
4. Refresh the page (press F5). See on the Network tab, there will be a few lines. Probably 7 lines.
5. You will only need to concern on 3 lines: `index.html`.
6. So, first your browser was requesting `index.html` from our server. The browser then parse the `head` part where we include some scripts and a font to import. Then browser will download them. That's why you see those 6 lines that we don't need to concern.
7. After all scripts are loaded,  it will show a login page.
8. After you insert username and password, the client app will send HTTP request to server with POST method, and body contains JSON formatted data of username and password.
9. Server will check the username and password validity through querying into the `requestors` table.  Once a row found, it will give `Requestor` object as the response with additional created token.
10. Received the token, script will re-rendered, and show `Patients` object with attribute `token` is set.
11. The browser is then request for `http://localhost:8080/v1/patients` or in the panel we only see `patients`. See that it will also add a header named **token** in the request.
12. Open new tab, then navigate to `http://localhost:8080/v1/patients`. What you see as the result? It's called JSON formatted data that our REST API serving.
13. So, in the deeper background, our server app (which serve path `v1/patients`) is querying into the `rest` schema on table `patients`, format the result into JSON, then present it as response to our request.
14. Receiving this response, our script at `cms/index.html` is then parsing it then render new DOM to replace the existing view.
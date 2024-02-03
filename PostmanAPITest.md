pm.test("GET istekleri 200 status code dönmektedir", function () {
    pm.response.to.have.status(200);
});

pm.test("Response içeriği doğru", function () {
    pm.response.to.be.json;

    pm.expect(pm.response.json()).to.have.property('data');

    pm.expect(pm.response.json().data).to.be.an('array');

    pm.response.json().data.forEach(function (flight) {
        pm.expect(flight).to.have.property('id');
        pm.expect(flight).to.have.property('from');
        pm.expect(flight).to.have.property('to');
        pm.expect(flight).to.have.property('date');
    });
});

pm.test("Header kontolü", function () {
    pm.response.to.have.header('Content-Type');
    pm.expect(pm.response.headers.get('Content-Type')).to.include('application/json');
});

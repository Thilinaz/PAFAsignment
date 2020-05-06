$(document).ready(function() {

    $("#alertSuccess").hide();
    $("#alertError").hide();

});

// Save
$(document).on("click", "#btnSave", function(event) {

    // Clear alerts
    $("#alertSuccess").text("");
    $("#alertSuccess").hide();
    $("#alertError").text("");
    $("#alertError").hide();

    // Form validation
    var status = validateHospitalForm();
    if (status != true) {
        $("#alertError").text(status);
        $("#alertError").show();
        return;
    }

    // If valid
    var type = ($("#hidHospitalIDSave").val() == "") ? "POST" : "PUT";

    $.ajax(
        {
            url : "HospitalsAPI",
            type : type,
            data : $("#formHospital").serialize(),
            dataType : "text",
            complete : function(response, status)
            {
                onHospitalSaveComplete(response.responseText, status);
            }
        });

});

function onHospitalSaveComplete(response, status) {

    if (status == "success") {

        var resultSet = JSON.parse(response);

        if (resultSet.status.trim() == "success") {

            $("#alertSuccess").text("Successfully saved.");
            $("#alertSuccess").show();
            $("#divHospitalsGrid").html(resultSet.data);

        } else if (resultSet.status.trim() == "error") {

            $("#alertError").text(resultSet.data);
            $("#alertError").show();

        }
    } else if (status == "error") {

        $("#alertError").text("Error while saving.");
        $("#alertError").show();

    } else {

        $("#alertError").text("Unknown error while saving..");
        $("#alertError").show();

    }

    $("#hidItemIDSave").val("");
    $("#formItem")[0].reset();

}

// Update
$(document).on("click", ".btnUpdate", function(event)
{
    $("#hidHospitaIDSave").val($(this).closest("tr").find('#hidHospitalIDUpdate').val());
    $("#hospitalName").val($(this).closest("tr").find('td:eq(0)').text());
    $("#hospitalAddress").val($(this).closest("tr").find('td:eq(1)').text());
    $("#hospitalUsername").val($(this).closest("tr").find('td:eq(2)').text());
    $("#hospitalPassword").val($(this).closest("tr").find('td:eq(3)').text());
});

//Remove
$(document).on("click", ".btnRemove", function(event)
{
    $.ajax(
        {
            url : "HospitalAPI",
            type : "DELETE",
            data : "hospitalID=" + $(this).data("hospitalid"),
            dataType : "text",
            complete : function(response, status)
            {
                onHospitalDeleteComplete(response.responseText, status);
            }
        });
});

function onHospitalDeleteComplete(response, status) {

    if (status == "success") {

        var resultSet = JSON.parse(response);

        if (resultSet.status.trim() == "success") {

            $("#alertSuccess").text("Successfully deleted.");
            $("#alertSuccess").show();
            $("#divHospitalsGrid").html(resultSet.data);

        } else if (resultSet.status.trim() == "error") {

            $("#alertError").text(resultSet.data);
            $("#alertError").show();

        }

    } else if (status == "error") {

        $("#alertError").text("Error while deleting.");
        $("#alertError").show();

    } else {

        $("#alertError").text("Unknown error while deleting..");
        $("#alertError").show();

    }

}

// Client Model
function validateHospitalForm() {

    
    if ($("#hospitalName").val().trim() == "")
    {
        return "Insert Hospital Name.";
    }
    
    if ($("#hospitalAddress").val().trim() == "")
    {
        return "Insert Item Name.";
    }

    
    if ($("#hospitalUsername").val().trim() == "")
    {
        return "Insert Username.";
    }
    
     if ($("#hospitalPassword").val().trim() == "")
    
    {
        return "Insert the password.";
    }
    
    return true;
}

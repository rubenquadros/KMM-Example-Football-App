package com.ruben.remote.exceptions

/**
 * Created by Ruben Quadros on 15/10/21
 **/
sealed class RemoteException(val code: Int, override val message: String?): Exception(message) {

    class ServerError(code: Int): RemoteException(code, "There was an error at server")

    class ClientError(code: Int): RemoteException(code, "There was an error with client params")

    class RedirectError(code: Int): RemoteException(code, "The resource has been moved")

    object UnknownError: RemoteException(-1, "There was an unexpected error")

}
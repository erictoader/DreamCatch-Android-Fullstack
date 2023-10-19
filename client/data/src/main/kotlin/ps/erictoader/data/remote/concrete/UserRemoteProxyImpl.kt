package ps.erictoader.data.remote.concrete

import ps.erictoader.data.remote.abstraction.UserRemoteProxy
import ps.erictoader.data.remote.api.UserApi

class UserRemoteProxyImpl(
    private val api: UserApi
) : UserRemoteProxy {

}
